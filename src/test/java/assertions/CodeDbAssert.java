package assertions;

import com.hcodez.codeengine.model.CodeDb;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class CodeDbAssert extends AbstractAssert<CodeDbAssert, CodeDb> {

    public CodeDbAssert(CodeDb actual) {
        super(actual, CodeDbAssert.class);
    }

    public static CodeDbAssert assertThat(CodeDb actual) {
        return new CodeDbAssert(actual);
    }

    public CodeDbAssert isEqualTo(CodeDb code) {

        isNotNull();

        Assertions.assertThat(actual.getCreateTime()).isEqualTo(code.getCreateTime());
        Assertions.assertThat(actual.getIdentifier()).isEqualTo(code.getIdentifier());
        Assertions.assertThat(actual.getName()).isEqualTo(code.getName());
        Assertions.assertThat(actual.getOwner()).isEqualTo(code.getOwner());
        Assertions.assertThat(actual.getPasscode()).isEqualTo(code.getPasscode());
        Assertions.assertThat(actual.getUrl()).isEqualTo(code.getUrl());
        Assertions.assertThat(actual.getUpdateTime()).isEqualTo(code.getUpdateTime());
        Assertions.assertThat(actual.getId()).isEqualTo(code.getId());
        Assertions.assertThat(actual.getOwnerId()).isEqualTo(code.getOwnerId());

        return this;
    }
}