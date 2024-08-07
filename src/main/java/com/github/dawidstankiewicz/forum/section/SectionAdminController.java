package com.github.dawidstankiewicz.forum.section;


import com.github.dawidstankiewicz.forum.config.Templates;
import com.github.dawidstankiewicz.forum.model.ForumModelMapper;
import com.github.dawidstankiewicz.forum.model.dto.NewSectionForm;
import com.github.dawidstankiewicz.forum.model.dto.SectionDto;
import com.github.dawidstankiewicz.forum.model.entity.Section;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/a/sections")
@Slf4j
public class SectionAdminController {

    private final SectionService sectionService;
    private final ForumModelMapper modelMapper;

    public SectionAdminController(SectionService sectionService, ForumModelMapper modelMapper) {
        this.sectionService = sectionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String getSectionsPage(Model model, Pageable pageable) {
        Page<Section> sections = sectionService.findSections(pageable);
        Page<SectionDto> dtos = modelMapper.mapPage(sections, SectionDto.class);
        model.addAttribute("sections", dtos);
        return Templates.ADMIN_SECTIONS_PANEL;
    }

    @GetMapping(value = "/new")
    public String getNewSectionForm(Model model) {
        model.addAttribute("newSection", new NewSectionForm());
        return "sections/new_section_form";
    }

    @PostMapping(value = "/new")
    public String processAndAddNewSection(
            @Valid @ModelAttribute("newSection") NewSectionForm newSection,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.mergeAttributes(result.getModel());
            return "sections/new_section_form";
        }

        Section section = new Section();
        section.setName(newSection.getName());
        section.setDescription(newSection.getDescription());
        section = sectionService.save(section);
        return "redirect:/sections/" + section.getId();
    }

    @DeleteMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id,
                         Authentication authentication,
                         RedirectAttributes model) {
//        User user = userService.findByUsername(authentication.getName());
// todo tests and checking if user is admin
//        if (!user.getRoles().contains(adminRole)) {
//            return "redirect:/section/" + id;
//        }
        sectionService.delete(id);

        model.addFlashAttribute("message", "section.successfully.deleted");
        return "redirect:/home";
    }

}
