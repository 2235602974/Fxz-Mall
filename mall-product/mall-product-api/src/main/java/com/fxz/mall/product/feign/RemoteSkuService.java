package com.fxz.mall.product.feign;

import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.mp.result.Result;
import com.fxz.mall.product.constant.FxzServerConstant;
import com.fxz.mall.product.dto.CheckPriceDTO;
import com.fxz.mall.product.dto.LockStockDTO;
import com.fxz.mall.product.dto.SkuInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-05-14 20:38
 */
@FeignClient(contextId = "remoteSkuService", value = FxzServerConstant.FXZ_MALL_PRODUCT)
public interface RemoteSkuService {

	@GetMapping("/app/sku/{skuId}/info")
	Result<SkuInfoDTO> getSkuInfo(@PathVariable("skuId") Long skuId);

	@PostMapping("/app/sku/price/check")
	Result<Boolean> checkPrice(@RequestBody CheckPriceDTO checkPriceDTO);

	@PutMapping("/app/sku/lock")
	Result<Boolean> lockStock(@RequestBody LockStockDTO lockStockDTO);

	@PutMapping("/app/sku/deduct")
	Result<Boolean> deductStock(String orderToken);

	@PutMapping("/app/sku/unlock")
	Result<Boolean> unlockStock(String orderToken, @RequestHeader(SecurityConstants.FROM) String form);

}
