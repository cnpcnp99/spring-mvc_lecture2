package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    MessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();

    @Test
    public void messageCodesResolverObject() throws Exception {
        // given
        String[] messageCodes = messageCodesResolver.resolveMessageCodes("required", "item");

        // when
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        // then
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    public void messageCodesResolverField() throws Exception {
        // given
        String[] messageCodes = messageCodesResolver.resolveMessageCodes("required", "item", "itemName", String.class);

        // when
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        // then
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }
}
