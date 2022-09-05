package hello.itemservice.controller;

import hello.itemservice.domain.Member;
import hello.itemservice.repository.MemberRepository;
import hello.itemservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/members";
    }

    @GetMapping("/{userId}/delete")
    public String memberDelete(@PathVariable Long userId, Model model) {
        model.addAttribute("user", memberService.findMember(userId));
        return "members/deleteForm";
    }

    //PRG 패턴 적용
    @PostMapping("/{userId}/delete")
    public String memberDelete(@PathVariable Long userId) {
        memberService.deleteMember(userId);
        return "redirect:/members";
    }

    @GetMapping("/{userId}")
    public String member(@PathVariable long userId, Model model) {
        Member member = memberService.findMember(userId);
        model.addAttribute("user", member);
        return "members/member";
    }

    @GetMapping("/add")
    public String addMember() {
        return "members/addForm";
    }


    //PRG 패턴 적용 RedirectAttributes를 사용해서 일정정보를 같이 넘길 수 있다.
    @PostMapping("/add")
    public String addMember(Member member, RedirectAttributes redirectAttributes) {
        memberService.saveMember(member);
        redirectAttributes.addAttribute("userId", member.getId());
        redirectAttributes.addAttribute( "status", true);
        return "redirect:/members/{userId}";
    }

    @GetMapping("/{userId}/edit")
    public String editMember(@PathVariable Long userId, Model model) {
        Member member = memberService.findMember(userId);
        model.addAttribute("user", member);
        return "members/editForm";
    }

    @PostMapping("/{userId}/edit")
    public String editMember(@PathVariable Long userId, @ModelAttribute Member member) {
        memberService.updateMember(member.getId(), member);
        return "redirect:/members/{userId}";
    }



}
