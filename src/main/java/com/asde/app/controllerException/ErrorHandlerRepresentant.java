package com.asde.app.controllerException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ErrorHandlerRepresentant {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String exceptionParamDeleteRepresentant(MethodArgumentTypeMismatchException ex,
                                                   Model model, RedirectAttributes notify){
        notify.addFlashAttribute("error", "No se puede eliminar el registro, intente con uno valido");
        return "redirect:/";
    }

}
