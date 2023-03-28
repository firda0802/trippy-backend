package com.java.pabw.trippy.app.DTO;

import com.java.pabw.trippy.app.utillity.Contanst;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Messages {
    private Integer responseCode;
    private String responseMessage;
    private Object data;
    private PagingResponse paging;

    public void success() {
        this.responseCode = Contanst.OK;
        this.responseMessage = Contanst.SUKSES;
    }
    public void notFound() {
        this.responseCode = Contanst.NO_CONTENT;
        this.responseMessage = "Data tidak ditemukan";
    }
    public Messages() {
    }

    public Messages(Integer responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
