package com.hcodez.codeengine.model;

import com.google.gson.annotations.Expose;

/**
 * Bean that represents a code exactly as it is in the database(used in the server).
 */
public class CodeDb extends Code {

    @Expose(serialize = false)
    private Integer id;

    @Expose(serialize = false)
    private Integer ownerId;


    public CodeDb() {

    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
