package com.example.woodraw.controller.dto.form;

import com.example.woodraw.domain.form.Form;
import com.example.woodraw.domain.product.Size;

import java.time.LocalDateTime;

public class FormUpdateDto {

    private Long formId;

    private Long memberId;

    private Long eventId;

    private LocalDateTime submission;

    private Size size;

    public Form toForm() {
        return new Form(this.formId, this.memberId, this.eventId, this.submission, this.size);
    }

    public FormUpdateDto(Long formId, Long memberId, Long eventId, LocalDateTime submission,
                         Size size) {
        this.formId = formId;
        this.memberId = memberId;
        this.eventId = eventId;
        this.submission = submission;
        this.size = size;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getFormId() {
        return formId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getEventId() {
        return eventId;
    }

    public LocalDateTime getSubmission() {
        return submission;
    }

    public Size getSize() {
        return size;
    }
}
