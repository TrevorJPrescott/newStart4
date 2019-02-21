package com.trevorpc.newstart4.model.object;

public class Response {


    private Integer duration;

    private Integer risetime;

    public Response(Integer duration, Integer risetime){
        this.duration = duration;
        this.risetime = risetime;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getRisetime() {
        return risetime;
    }



}