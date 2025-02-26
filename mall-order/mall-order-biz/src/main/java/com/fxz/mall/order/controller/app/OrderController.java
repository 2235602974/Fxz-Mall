package com.fxz.mall.order.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.core.enums.IBaseEnum;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.order.dto.OrderSubmitDTO;
import com.fxz.mall.order.enums.PayTypeEnum;
import com.fxz.mall.order.query.OrderPageQuery;
import com.fxz.mall.order.service.impl.OrderServiceImpl;
import com.fxz.mall.order.vo.OrderConfirmVO;
import com.fxz.mall.order.vo.OrderPageVO;
import com.fxz.mall.order.vo.OrderSubmitVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/5/14 17:44
 */
@RestController("appOrderController")
@RequestMapping("/app/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderServiceImpl orderService;

	/**
	 * 订单确认 → 进入创建订单页面
	 * <p>
	 * 获取购买商品明细、用户默认收货地址、防重提交唯一token 进入订单创建页面有两个入口，1：立即购买；2：购物车结算
	 * @param skuId 直接购买必填，购物车结算不填
	 * @return OrderConfirmVO
	 */
	@PostMapping("/confirm")
	public Result<OrderConfirmVO> confirm(@RequestParam(required = false) Long skuId) {
		return Result.success(orderService.confirm(skuId));
	}

	/**
	 * 订单提交
	 */
	@PostMapping("/submit")
	public Result<OrderSubmitVO> submit(@RequestBody OrderSubmitDTO orderSubmitDTO) {
		return Result.success(orderService.submit(orderSubmitDTO));
	}

	/**
	 * 订单支付
	 * @param orderId 订单id
	 * @param payType 支付方式
	 * @param appId 小程序appId
	 */
	@PostMapping("/{orderId}/pay")
	public <T> Result<T> pay(@PathVariable Long orderId, Integer payType, String appId) {
		PayTypeEnum payTypeEnum = IBaseEnum.getEnumByValue(payType, PayTypeEnum.class);
		if (payTypeEnum == null) {
			return Result.failed("系统暂不支持该支付方式~");
		}

		return Result.success(orderService.pay(orderId, appId, payTypeEnum));
	}

	/**
	 * 删除订单
	 * @param orderId 订单id
	 */
	@DeleteMapping("/{orderId}")
	public <T> Result<T> deleteOrder(@PathVariable Long orderId) {
		return Result.judge(orderService.deleteOrder(orderId));
	}

	/**
	 * 订单取消
	 * @param orderId 订单id
	 */
	@PutMapping("/cancel")
	public <T> Result<T> cancel(@RequestParam Long orderId) {
		return Result.judge(orderService.cancelOrder(orderId));
	}

	/**
	 * 分页查询
	 * @param queryParams 分页参数
	 */
	@GetMapping
	public Result<PageResult<OrderPageVO>> listOrderPages(OrderPageQuery queryParams) {
		Page<OrderPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
		return Result.success(PageResult.success(orderService.listOrderPages(page, queryParams)));
	}

}
