package com.arrange.play.model.entity;

import java.util.List;
import lombok.Data;

@Data
public class HttpRequestModule {

    private Integer id;

    private Integer apiId;

    private String moduleName;

    private String header;

    private String moduleContent;

    private List<String> paramKeys;

    private String moduleDesc;

}
