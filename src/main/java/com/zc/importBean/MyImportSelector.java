package com.zc.importBean;

import com.zc.model.Blue;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Date 2021/3/17 14:18
 * @Author zc
 */
public class MyImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Blue.class.getName()};
    }
}
