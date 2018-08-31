package com.rb.parser.response;

public class Response {
	public Object data;
	public Status status;

	public Response(Object data, Status status) {
		this.data = data;
		this.status = status;
	}

}
