package com.quick.start.ddd.domain.model.vo;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;
import java.util.Objects;

import static com.quick.start.ddd.domain.model.vo.QuestionUpdatedRecord.UpdateType.DETAIL_EDITED;
import static com.quick.start.ddd.domain.model.vo.QuestionUpdatedRecord.UpdateType.TITLE_EDITED;
import static com.quick.start.ddd.util.OffsetDateTimes.currentTime;
import static com.quick.start.ddd.domain.model.vo.QuestionUpdatedRecord.UpdateType.CREATED;

@Embeddable
public class QuestionUpdatedRecord {

    @Enumerated(EnumType.STRING)
    private UpdateType updateType;
    private String updaterId;
    private OffsetDateTime updatedAt;
    private String reason;
    private String createdTitle;
    private String createdDetail;
    private String uneditedTitle;
    private String editedTitle;
    private String uneditedDetail;
    private String editedDetail;

    public static QuestionUpdatedRecord ofCreated(String updaterId, String createdTitle, String createdDetail) {
        return new QuestionUpdatedRecord(CREATED, updaterId, currentTime(), null, createdTitle, createdDetail, null, null, null, null);
    }

    public static QuestionUpdatedRecord ofTitleEdited(String updaterId, String reason, String uneditedTitle, String editedTitle) {
        return new QuestionUpdatedRecord(TITLE_EDITED, updaterId, currentTime(), reason, null, null, uneditedTitle, editedTitle, null, null);
    }

    public static QuestionUpdatedRecord ofDetailEdited(String updaterId, String reason, String uneditedDetail, String editedDetail) {
        return new QuestionUpdatedRecord(DETAIL_EDITED, updaterId, currentTime(), reason, null, null, null, null, uneditedDetail, editedDetail);
    }

    @PersistenceConstructor
    public QuestionUpdatedRecord() {
    }

    private QuestionUpdatedRecord(UpdateType updateType, String updaterId, OffsetDateTime updatedAt, String reason,
                                  String createdTitle, String createdDetail,
                                  String uneditedTitle, String editedTitle,
                                  String uneditedDetail, String editedDetail) {
        this.updateType = updateType;
        this.updaterId = updaterId;
        this.updatedAt = updatedAt;
        this.reason = reason;
        this.createdTitle = createdTitle;
        this.createdDetail = createdDetail;
        this.uneditedTitle = uneditedTitle;
        this.editedTitle = editedTitle;
        this.uneditedDetail = uneditedDetail;
        this.editedDetail = editedDetail;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getReason() {
        return reason;
    }

    public String getCreatedTitle() {
        return createdTitle;
    }

    public String getCreatedDetail() {
        return createdDetail;
    }

    public String getUneditedTitle() {
        return uneditedTitle;
    }

    public String getEditedTitle() {
        return editedTitle;
    }

    public String getUneditedDetail() {
        return uneditedDetail;
    }

    public String getEditedDetail() {
        return editedDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionUpdatedRecord that)) return false;
        // QuestionUpdatedRecord that = (QuestionUpdatedRecord) o;
        return updateType == that.updateType
                && Objects.equals(updaterId, that.updaterId)
                && Objects.equals(updatedAt, that.updatedAt)
                && Objects.equals(reason, that.reason)
                && Objects.equals(createdTitle, that.createdTitle)
                && Objects.equals(createdDetail, that.createdDetail)
                && Objects.equals(uneditedTitle, that.uneditedTitle)
                && Objects.equals(editedTitle, that.editedTitle)
                && Objects.equals(uneditedDetail, that.uneditedDetail)
                && Objects.equals(editedDetail, that.editedDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updateType, updaterId, updatedAt, reason,
                createdTitle, createdDetail, uneditedTitle, editedTitle, uneditedDetail, editedDetail);
    }

    public enum UpdateType {
        CREATED, TITLE_EDITED, DETAIL_EDITED
    }
}
