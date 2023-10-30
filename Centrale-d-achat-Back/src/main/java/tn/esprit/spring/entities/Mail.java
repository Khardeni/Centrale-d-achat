package tn.esprit.spring.entities;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class Mail {
    private String from;
    private String to;
    private String subject;
    private List<Object> attachments;
    private Map<String, Object> props;

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }
}
