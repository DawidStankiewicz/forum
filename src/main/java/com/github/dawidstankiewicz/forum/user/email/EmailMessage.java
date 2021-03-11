package com.github.dawidstankiewicz.forum.user.email;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {

    private String recipient;
    private String subject;
    private String content;
}
