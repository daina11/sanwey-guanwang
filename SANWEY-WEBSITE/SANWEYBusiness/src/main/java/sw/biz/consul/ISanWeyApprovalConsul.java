package sw.biz.consul;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sw.core.web.Message;

/**
 * 审批系统
 * @author wwh
 * @date 2022/9/19-10:12
 * @email 644129971@qq.com
 */
@FeignClient(name = "sanWeyApproval")
public interface ISanWeyApprovalConsul {

    @PostMapping(value = "/apv/consul/approval/apply/submit")
    Message approvalApplySubmit(@RequestBody String data);

    @PostMapping(value = "/apv/consul/approval/apply/cancel")
    Message approvalApplyCancel(@RequestBody String data);
}
