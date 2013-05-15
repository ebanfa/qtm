/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationModuleResponse 
 * @author Edward Banfa
 */
public class ApplicationModuleResponse extends BaseResponse {

    private String description;
    private int sequenceNo;
    private String displayNm;
    private String displayImg;
    private char displayFg;

    public ApplicationModuleResponse() {
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public int getSequenceNo() {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getDisplayNm() {
        return this.displayNm;
    }
    
    public void setDisplayNm(String displayNm) {
        this.displayNm = displayNm;
    }

    public String getDisplayImg() {
        return this.displayImg;
    }
    
    public void setDisplayImg(String displayImg) {
        this.displayImg = displayImg;
    }

    public char getDisplayFg() {
        return this.displayFg;
    }
    
    public void setDisplayFg(char displayFg) {
        this.displayFg = displayFg;
    }


}


