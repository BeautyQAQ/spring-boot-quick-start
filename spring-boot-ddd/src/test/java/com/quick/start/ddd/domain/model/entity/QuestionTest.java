package com.quick.start.ddd.domain.model.entity;

import static com.quick.start.ddd.domain.model.vo.QuestionUpdatedRecord.UpdateType.CREATED;
import static com.quick.start.ddd.domain.model.vo.QuestionUpdatedRecord.UpdateType.DETAIL_EDITED;
import static com.quick.start.ddd.domain.model.vo.QuestionUpdatedRecord.UpdateType.TITLE_EDITED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.quick.start.ddd.domain.model.vo.QuestionUpdatedRecord;
import java.util.List;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    void should_generate_created_record_after_question_created() {
        Question question = new Question("UID_00001", "A test title", "A test detail");
        List<QuestionUpdatedRecord> updatedRecords = question.getUpdatedRecords();
        assertThat(updatedRecords, hasSize(1));
        QuestionUpdatedRecord questionCreatedRecord = updatedRecords.get(0);
        assertThat(questionCreatedRecord.getUpdateType(), is(CREATED));
        assertThat(questionCreatedRecord.getCreatedTitle(), is(question.getTitle()));
        assertThat(questionCreatedRecord.getCreatedDetail(), is(question.getDetail()));
    }

    @Test
    void should_generate_edited_record_after_question_edited() {
        String originalTitle = "A test title";
        String originalDetail = "A test detail";
        Question question = new Question("UID_00001", originalTitle, originalDetail);
        String editedTitle = "A new test title";
        question.editTitle("UID_00002", "for test", editedTitle);
        String editedDetail = "A new test detail";
        question.editDetail("UID_00003", "for test", editedDetail);
        List<QuestionUpdatedRecord> updatedRecords = question.getUpdatedRecords();
        assertThat(updatedRecords, hasSize(3));
        QuestionUpdatedRecord questionCreatedRecord = updatedRecords.get(0);
        assertThat(questionCreatedRecord.getUpdateType(), is(CREATED));
        assertThat(questionCreatedRecord.getCreatedTitle(), is(originalTitle));
        assertThat(questionCreatedRecord.getCreatedDetail(), is(originalDetail));
        QuestionUpdatedRecord questionTitleEditedRecord = updatedRecords.get(1);
        assertThat(questionTitleEditedRecord.getUpdateType(), is(TITLE_EDITED));
        assertThat(questionTitleEditedRecord.getUneditedTitle(), is(originalTitle));
        assertThat(questionTitleEditedRecord.getEditedTitle(), is(editedTitle));
        QuestionUpdatedRecord questionDetailEditedRecord = updatedRecords.get(2);
        assertThat(questionDetailEditedRecord.getUpdateType(), is(DETAIL_EDITED));
        assertThat(questionDetailEditedRecord.getUneditedDetail(), is(originalDetail));
        assertThat(questionDetailEditedRecord.getEditedDetail(), is(editedDetail));

        assertThat(question.getTitle(), is(editedTitle));
        assertThat(question.getDetail(), is(editedDetail));
    }
}
