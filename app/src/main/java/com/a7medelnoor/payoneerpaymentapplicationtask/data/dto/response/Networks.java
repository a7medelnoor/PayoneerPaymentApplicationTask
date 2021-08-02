package com.a7medelnoor.payoneerpaymentapplicationtask.data.dto.response;


import java.util.List;

public class Networks {
  public List<Applicable> applicable;

  public Networks(List<Applicable> applicable) {
    this.applicable = applicable;
  }

  public List<Applicable> getApplicable() {
    return applicable;
  }

  public void setApplicable(List<Applicable> applicable) {
    this.applicable = applicable;
  }
}
