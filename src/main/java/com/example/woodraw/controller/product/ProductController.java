package com.example.woodraw.controller.product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.woodraw.controller.dto.product.ProductRequestDto;
import com.example.woodraw.controller.dto.product.ProductResponseDto;
import com.example.woodraw.controller.dto.product.ProductUpdateDto;
import com.example.woodraw.service.product.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/api/v1/products")
	public String findAll(Model model) {
		List<ProductResponseDto> productList = productService.findAll();
		model.addAttribute("productList", productList);
		return "product/products";
	}

	@GetMapping("/api/v1/product/{productId}")
	public String findById(@PathVariable Long productId,Model model) {
		ProductResponseDto productResponseDto = productService.findById(productId);
		model.addAttribute("product", productResponseDto);
		return "product/productInfo";
	}

	@GetMapping("/api/v1/product")
	public String create() {
		return "product/productForm";
	}

	@PostMapping("/api/v1/product")
	public String insert(@ModelAttribute ProductRequestDto productRequestDto) {
		System.out.println("productRequestDto: " + productRequestDto.getProductName() + productRequestDto.getPrice());
		productService.insert(productRequestDto);
		return "redirect:/api/v1/products";
	}

	@GetMapping("/api/v1/product/{productId}/edit")
	public String edit(@PathVariable Long productId, Model model) {
		ProductResponseDto productResponseDto = productService.findById(productId);
		model.addAttribute("product", productResponseDto);
		return "product/productEditForm";
	}

	@PatchMapping("/api/v1/product/{productId}/edit")
	public String updateByObject(@PathVariable Long productId, @ModelAttribute ProductUpdateDto productUpdateDto) {
		productService.updateByObject(productUpdateDto);
		return "redirect:/api/v1/products";
	}

	@DeleteMapping("/api/v1/product/{productId}")
	public String delete(@PathVariable Long productId) {
		productService.deleteById(productId);
		return "redirect:/api/v1/products";
	}
}
