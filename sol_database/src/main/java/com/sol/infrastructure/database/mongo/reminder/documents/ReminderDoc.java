package com.sol.infrastructure.database.mongo.reminder.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.reminder.entity.ReminderEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuperBuilder
@NoArgsConstructor
@Getter
public class ReminderDoc extends ReminderEntity {

 
}
