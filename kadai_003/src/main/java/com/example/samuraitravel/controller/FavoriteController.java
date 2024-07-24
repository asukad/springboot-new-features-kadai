package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.FavoriteRepository;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.FavoriteService;

@Controller
public class FavoriteController {
	
	private final FavoriteRepository favoriteRepository;	
	private final FavoriteService favoriteService;
	private final HouseRepository houseRepository;
	
	
	public FavoriteController(FavoriteRepository favoriteRepository, FavoriteService favoriteService, 
		                        HouseRepository houseRepository) {
		this.favoriteRepository = favoriteRepository;		
		this.favoriteService = favoriteService;
		this.houseRepository = houseRepository;			
	}
	
	// お気に入り一覧
	@GetMapping("/favorite")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
        User user = userDetailsImpl.getUser();
        Page<Favorite> favoritePage = favoriteRepository.findByUserOrderByCreatedAtDesc(user, pageable);
        
        model.addAttribute("favoritePage", favoritePage);         
        
        return "favorite/index";
    }
	
	// お気に入り登録
	@PostMapping("/houses/{id}/favorite/add")
	public String addFavorite(@PathVariable(name = "id") Integer houseId,
	                          @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
	                          RedirectAttributes redirectAttributes) {
	    if (userDetailsImpl == null) {
	        redirectAttributes.addFlashAttribute("message", "ユーザーが認証されていません");
	        return "redirect:/login";
	    }

	    User user = userDetailsImpl.getUser();
	    House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));
	    favoriteService.add(house, user);

	    redirectAttributes.addFlashAttribute("message", "お気に入りに追加しました");
	    return "redirect:/houses/" + houseId;
	}

	// お気に入り解除
	@PostMapping("/houses/{id}/favorite/delete")
	public String deleteFavorite(@PathVariable(name = "id") Integer houseId,
	                             @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
	                             RedirectAttributes redirectAttributes) {
	    if (userDetailsImpl == null) {
	        redirectAttributes.addFlashAttribute("message", "ユーザーが認証されていません");
	        return "redirect:/login";
	    }

	    User user = userDetailsImpl.getUser();
	    House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));
	    favoriteService.delete(house, user);

	    redirectAttributes.addFlashAttribute("message", "お気に入りを解除しました");
	    return "redirect:/houses/" + houseId;
	}


}
