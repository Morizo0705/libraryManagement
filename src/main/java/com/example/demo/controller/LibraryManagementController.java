package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BookReturnRequest;
import com.example.demo.dto.BookSearchRequest;
import com.example.demo.dto.BookUpdateRequest;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserSearchRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.BookInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.LoginForm;
import com.example.demo.service.BookInfoService;
import com.example.demo.service.BookReturnService;
import com.example.demo.service.BookUpdateService;
import com.example.demo.service.UserAddService;
import com.example.demo.service.UserInfoService;

@Controller
public class LibraryManagementController {
	
	@GetMapping("/home/index")
	public String indexpage() {
		return "home/index";
	}

	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	private BookInfoService bookInfoService;

	@GetMapping(value = "/lib/booklist")
	public String libdisplay(Model model) {
		List<BookInfo> bookList = bookInfoService.findAll();
		model.addAttribute("booklist", bookList);
		model.addAttribute("bookSearchRequest", new BookSearchRequest());
		return "lib/booklist";
	}
	
	@GetMapping(value = "/lending/lend")
	public String displayList(Model model) {
		List<BookInfo> bookList = bookInfoService.findAll();
		model.addAttribute("booklist", bookList);
		model.addAttribute("bookSearchRequest", new BookSearchRequest());
		return "lending/lend";
	}


	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	private UserAddService userAddService;

	/**
	 * ユーザー新規登録画面を表示
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping(value = "/user/add")
	public String displayAdd(Model model) {
		model.addAttribute("userAddRequest", new UserAddRequest());
		return "user/add";
	}
	/**
	 * ユーザー新規登録
	 * @param userAddRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute UserAddRequest userAddRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "user/add";
		}
		// ユーザー情報の登録
		userAddService.save(userAddRequest);
		return "redirect:/user/add";
	}

	/**
	 * ユーザー更新
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */

	@Autowired
	private BookUpdateService bookUpdateService;

	@RequestMapping(value = "/lend/update", method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute BookUpdateRequest bookUpdateRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "lending/lend";
		}
		// ユーザー情報の更新
		bookUpdateService.update(bookUpdateRequest);
		bookUpdateService.insert(bookUpdateRequest);
		return "redirect:/lending/lend";
	} 


	

	@GetMapping(value = "/return/return")
	public String displayReturn(Model model) {
		model.addAttribute("bookReturnRequest", new BookReturnRequest());
		return "return/return";
	}
	/**
	 * ユーザー更新
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */

	@Autowired
	private BookReturnService bookReturnService;

	@RequestMapping(value = "/return/update", method = RequestMethod.POST)
	public String bookreturn(@Validated @ModelAttribute BookReturnRequest bookReturnRequest, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "return/return";
		}
		// ユーザー情報の更新
		bookReturnService.renthisupdate(bookReturnRequest);
		bookReturnService.libupdate(bookReturnRequest);
	    redirectAttributes.addFlashAttribute("successMessage", "返却が完了しました。本を棚に返却して下さい。");
		return "redirect:/return/return";
	}
	
	
	//--------------------------------------------管理者系----------------------------------------------------
	//管理者用ログインユーザー名、パスワード
	private static final String LOGIN_ID = "user";
	private static final String PASSWORD = "pwd";
	
	@GetMapping("/admin/login")
	public String view(Model model, LoginForm form) {
		
		return "admin/login";
	}
	
	@PostMapping("/admin/login")
	public String login(Model model, LoginForm form) {
		var isCorrectUserAuth = form.getLoginId().equals(LOGIN_ID) && form.getPassword().equals(PASSWORD);
		
		if(isCorrectUserAuth) {
			return "redirect:user/list";
		}
		else {
			model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが間違っています。");
			return "admin/login";
		}
	}
	
	
    /**
     * ユーザー情報 Service
     */
    @Autowired
    private UserInfoService userInfoService;

    /**
     * ユーザー情報一覧画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @GetMapping(value = "/admin/user/list")
    public String displayuserList(Model model) {
        List<UserInfo> userList = userInfoService.findAll();
        model.addAttribute("userlist", userList);
        model.addAttribute("userSearchRequest", new UserSearchRequest());
        return "admin/user/search";
    }
    
    /**
     * ユーザー編集画面を表示
     * @param id ユーザーID
     * @param model Model
     * @return ユーザー編集画面
     */
    @GetMapping("/user/{syaId}/edit")
    public String displayEdit(@PathVariable String syaId, Model model) {
        UserInfo user = userInfoService.findById(syaId);
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setSyaId(user.getSyaId());
        userUpdateRequest.setSyaId(user.getSyaId());
        userUpdateRequest.setName(user.getName());
        userUpdateRequest.setAddress(user.getAddress());
        userUpdateRequest.setPhone(user.getPhone());
        userUpdateRequest.setMail(user.getMail());
        userUpdateRequest.setPassword(user.getPassword());
        model.addAttribute("userUpdateRequest", userUpdateRequest);
        return "admin/user/edit";
    }

    /**
     * 
     * ユーザー情報検索
     * @param userSearchRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/search", method = RequestMethod.POST)
    public String search(@ModelAttribute UserSearchRequest userSearchRequest, Model model) {
        List<UserInfo> userList = userInfoService.search(userSearchRequest);
        model.addAttribute("userlist", userList);
        return "admin/user/search";
    }

    /**
     * ユーザー更新
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報詳細画面
     */
    @RequestMapping(value = "admin/user/update", method = RequestMethod.POST)
    public String userupdate(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "admin/user/edit";
        }
        // ユーザー情報の更新
        userInfoService.update(userUpdateRequest);
        return "redirect:/admin/user/list";
    }
    
    /**
     * ユーザー情報削除（論理削除）
     * @param id ユーザーID
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @GetMapping("/user/{syaId}/delete")
    public String delete(@PathVariable String syaId, Model model) {
        // ユーザー情報の削除
        userInfoService.delete(syaId);
        return "redirect:/admin/user/list";
    }
}