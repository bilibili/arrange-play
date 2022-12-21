package com.arrange.play.service.node.handler;


import com.arrange.play.model.step.DoRequestDTO;
import com.arrange.play.model.step.DoResponseDTO;

public abstract class DoRequestBaseService {

  public abstract DoResponseDTO doRequest(DoRequestDTO doRequestDTO);

}
