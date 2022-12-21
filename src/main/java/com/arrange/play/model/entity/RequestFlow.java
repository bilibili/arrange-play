package com.arrange.play.model.entity;

import com.arrange.play.model.entity.inner.ReplaceMethod;
import com.arrange.play.model.entity.inner.StepNode;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * 请求流程
 */
@Data
public class RequestFlow {

    private Integer id;

    private List<StepNode> flowContent;

    private Map<String, ReplaceMethod> replaceValue;

    private String flowDesc;

}
