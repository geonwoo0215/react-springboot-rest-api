package com.example.woodraw.controller.dto.form;

import com.example.woodraw.domain.form.Form;
import com.example.woodraw.domain.product.Size;

import java.time.LocalDateTime;

public class FormUpdateDto {

    private Long formId;

    private Long eventId;

    private Size size;

    private String email;

    public Form toForm() {
        return new Form(this.formId, this.eventId, this.size, this.email);
    }

    public FormUpdateDto(Long formId, Long eventId, Size size, String email) {
        this.formId = formId;
        this.eventId = eventId;
        this.size = size;
        this.email = email;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getFormId() {
        return formId;
    }

    public Long getEventId() {
        return eventId;
    }

    public Size getSize() {
        return size;
    }

    public String getEmail() {
        return email;
    }
}
