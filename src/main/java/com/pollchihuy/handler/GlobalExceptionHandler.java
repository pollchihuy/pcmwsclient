package com.pollchihuy.handler;


import com.pollchihuy.config.OtherConfig;
import com.pollchihuy.util.LoggingFile;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/*
	KODE EXCEPTION
	VALIDATION		= 01
	DATA			= 02
	AUTH			= 03
	MEDIA / FILE	= 04
	EXTERNAL API	= 05
	UNKNOW			= 99
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private List<ApiValidationError> lsSubError = new ArrayList<ApiValidationError>();
	private String [] strExceptionArr = new String[2];

	public GlobalExceptionHandler() {
		strExceptionArr[0] = "GlobalExceptionHandler";
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers,
																  HttpStatusCode status,
																  WebRequest request) {
		lsSubError.clear();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			lsSubError.add(new ApiValidationError(fieldError.getField(),
					fieldError.getDefaultMessage(),
					fieldError.getRejectedValue()));
		}
		ApiError apiError =
				new ApiError(HttpStatus.BAD_REQUEST, "Data Tidak Valid !!",ex,request.getDescription(false),"X-01-001");
		apiError.setSubErrors(lsSubError);
		strExceptionArr[1] = "handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status,WebServlet request) \n";//perubahan 12-12-2023
		return new ResponseEntity<Object>(apiError,HttpStatus.BAD_REQUEST);
	}
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	@ExceptionHandler(UnexpectedRollbackException.class)
	public ResponseEntity<Object> unexpectedRollbackException(UnexpectedRollbackException ex, HttpServletRequest request) {
		strExceptionArr[1] = "UnexpectedRollbackException(UnexpectedRollbackException ex, HttpServletRequest request) \n";
		LoggingFile.exceptionStringz(strExceptionArr, ex, OtherConfig.getEnableLogFile());
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Proses Transaksi Bermasalah",ex,request.getPathInfo(),"X-02-002"));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> runtimeException(RuntimeException ex, HttpServletRequest request) {
		strExceptionArr[1] = "runtimeException(UnexpectedRollbackException ex, HttpServletRequest request) \n";
		LoggingFile.exceptionStringz(strExceptionArr, ex, OtherConfig.getEnableLogFile());
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Server Error Hubungi Polisi!!",ex,request.getPathInfo(),"X-02-003"));
	}
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, HttpServletRequest request) {
		LoggingFile.exceptionStringz(strExceptionArr, ex, OtherConfig.getEnableLogFile());
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"TIDAK DAPAT DIPROSES",ex,request.getPathInfo(),"X-99-001"));
	}
}