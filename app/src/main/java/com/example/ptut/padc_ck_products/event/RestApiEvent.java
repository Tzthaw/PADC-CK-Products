package com.example.ptut.padc_ck_products.event;

import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;

import java.util.List;

public class RestApiEvent {
    public class NewProductEvent{
        List<NewProductVO> newProductVOS;

        public NewProductEvent(List<NewProductVO> newProductVOS) {
            this.newProductVOS = newProductVOS;
        }

        public List<NewProductVO> getNewProductVOS() {
            return newProductVOS;
        }

    }

    public class ErrorMessageEvent{
        String msg;

        public ErrorMessageEvent(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }
}
