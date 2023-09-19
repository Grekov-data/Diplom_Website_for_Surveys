package org.datko.diplom_grekov.controller;

import lombok.RequiredArgsConstructor;
import org.datko.diplom_grekov.entity.Company;
import org.datko.diplom_grekov.entity.Survey;
import org.datko.diplom_grekov.service.SurveyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("survey")
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("")                                                 //вывод листа со списком опросов
    public String findAll(Model model){
        Iterable<Survey> surveys = surveyService.findAll();
        if (surveys.iterator().hasNext()) {
            model.addAttribute("surveys", surveys);
        } else {
            model.addAttribute("surveys", null);
        }
        return "survey/survey-list";
    }

    @GetMapping("new")                                              //переход на лист для создания нового опроса
    public String addNew(Model model) {
        Survey survey = new Survey();
        model.addAttribute("survey", survey);
        return "survey/survey-form";
    }

    @PostMapping("new")                                             //создание нового опроса
    public String addNew(Survey survey, RedirectAttributes ra) {
        Optional<Survey> newSurvey = surveyService.add(survey);
        if(newSurvey.isPresent()) {
            ra.addFlashAttribute("successMessage",
                    "Опрос " + survey.getName() + " успешно добавлен!");
        } else {
            ra.addFlashAttribute("errorMessage",
                    "Опрос " + survey.getName() + " уже зарегистрирован!");
        }
        return "redirect:/survey";
    }

    @GetMapping("/delete/{id}")                                      //удаление опроса
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        Optional<Survey> removed = surveyService.deleteById(id);
        if(removed.isPresent()) {
            ra.addFlashAttribute("successMessage",
                    "Опрос " + removed.get().getName() + " успешно удален!");
        } else {
            ra.addFlashAttribute("errorMessage",
                    "Некорректный id для удаления!");
        }
        return "redirect:/survey";
    }

    @GetMapping("{id}")                                             //лист с детальной информацией об опросе
    public String details(@PathVariable Integer id, Model model) {
        Optional<Survey> survey = surveyService.findById(id);
        if (survey.isPresent()) {
            model.addAttribute("survey", survey.get());
        } else {
            model.addAttribute("survey", null);
        }
        return "survey/survey-details";
    }

    @GetMapping("/update/{id}")                         //переход на лист для обновления данных существующего опроса
    public String updateExisting(@PathVariable Integer id, Model model) {
        Optional<Survey> survey = surveyService.findById(id);
        if (survey.isPresent()) {
            model.addAttribute("survey", survey.get());
        } else {
            model.addAttribute("survey", null);
        }
        return "survey/survey-form-update";
    }

    @PostMapping("/update/{id}")                        //обновление данных уже существующего опроса
    public String updateExisting(@PathVariable Integer id, Survey survey, RedirectAttributes ra) {
        Optional<Survey> updated = surveyService.updateById(id, survey);
        if (updated.isPresent()) {
            ra.addFlashAttribute("successMessage",
                    "Опрос успешно обновлен");
        } else {
            ra.addFlashAttribute("errorMessage",
                    "Опрос не был обновлен");
        }
        return "redirect:/survey";
    }
}