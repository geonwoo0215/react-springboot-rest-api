package com.example.woodraw.controller.productDetail;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.woodraw.controller.dto.productDetail.ProductDetailRequestDto;
import com.example.woodraw.controller.dto.productDetail.ProductDetailResponseDto;
import com.example.woodraw.controller.dto.productDetail.ProductDetailUpdateDto;
import com.example.woodraw.service.product.ProductDetailService;

@Controller
public class ProductDetailController {

	private final ProductDetailService productDetailService;

	public ProductDetailController(ProductDetailService productDetailService) {
		this.productDetailService = productDetailService;
	}

	@GetMapping("/api/v1/productDetails")
	public String findAll(Model model) {
		List<ProductDetailResponseDto> productDetailList = productDetailService.findAll();
		model.addAttribute("productDetailList", productDetailList);
		return "productDetail/productDetails";
	}

	@GetMapping("/api/v1/productDetail/{productDetailId}")
	public String findById(@PathVariable Long productDetailId,Model model) {
		ProductDetailResponseDto productDetailResponseDto = productDetailService.findById(productDetailId);
		System.out.println("updated : " + productDetailResponseDto.getDetailId() + productDetailResponseDto.getProductId());
		model.addAttribute("productDetail", productDetailResponseDto);
		return "productDetail/productDetailInfo";
	}

	@GetMapping("/api/v1/product/{productId}/productDetail")
	public String create(@PathVariable Long productId, Model model) {
		model.addAttribute("productId", productId);
		return "productDetail/productDetailForm";
	}

	@PostMapping("/api/v1/product/{productId}/productDetail")
	public String insert(@PathVariable Long productId, @ModelAttribute ProductDetailRequestDto productDetailRequestDto) {
		productDetailRequestDto.setProductId(productId);
		productDetailService.insert(productDetailRequestDto);
		return "redirect:/api/v1/productDetails";
	}

	@GetMapping("/api/v1/productDetail/{productDetailId}/edit")
	public String edit(@PathVariable Long productDetailId, Model model) {
		ProductDetailResponseDto productDetailResponseDto = productDetailService.findById(productDetailId);
		model.addAttribute("productDetail", productDetailResponseDto);
		return "productDetail/productDetailEditForm";
	}

	@PatchMapping("/api/v1/productDetail/{productDetailId}/edit")
	public String updateByObject(@PathVariable Long productDetailId, @ModelAttribute ProductDetailUpdateDto productDetailUpdateDto) {
		productDetailUpdateDto.setDetailId(productDetailId);
		productDetailService.updateByObject(productDetailUpdateDto);
		return "redirect:/api/v1/productDetails";
	}

	@DeleteMapping("/api/v1/productDetail/{productDetailId}")
	public String delete(@PathVariable Long productDetailId) {
		productDetailService.deleteById(productDetailId);
		return "redirect:/api/v1/productDetails";
	}

}
