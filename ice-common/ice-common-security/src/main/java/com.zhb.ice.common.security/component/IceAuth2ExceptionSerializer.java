package com.zhb.ice.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zhb.ice.common.security.exception.IceAuth2Exception;
import lombok.SneakyThrows;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 10:55
 * @Version 1.0
 */
public class IceAuth2ExceptionSerializer extends StdSerializer<IceAuth2Exception> {
    public IceAuth2ExceptionSerializer() {
        super(IceAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(IceAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {

        gen.writeStartObject();
        gen.writeObjectField("code", value.getStatus().getCode());
        gen.writeStringField("msg", value.getStatus().getMsg());
        gen.writeEndObject();
    }
}
