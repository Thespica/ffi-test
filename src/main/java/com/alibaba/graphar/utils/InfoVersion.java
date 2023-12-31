package com.alibaba.graphar.utils;

import com.alibaba.fastffi.*;
import com.alibaba.fastffi.stdcxx.StdString;
import com.alibaba.fastffi.stdcxx.StdVector;

import static com.alibaba.graphar.utils.CppClassName.GAR_INFO_VERSION;
import static com.alibaba.graphar.utils.CppHeaderName.GRAPH_INFO_H;

@FFIGen(library = "garffitest")
@CXXHead(GRAPH_INFO_H)
@FFITypeAlias(GAR_INFO_VERSION)
public interface InfoVersion extends FFIPointer {
    static InfoVersion create(int version) {
        return factory.create(version);
    }

    Factory factory = FFITypeFactory.getFactory(InfoVersion.class);

    @FFIFactory
    interface Factory {
        InfoVersion create(int version);
    }

    /**
     * describe the InfoVersion like toString, but return StdString
     * @return StdString that describe the InfoVersion
     */
    /*
    @FFINameAlias("ToString")
    StdString toStdString();
     */

    /**
     * get version integer
     * @return version integer
     */
    int version();

    /**
     * Check specific type in InfoVersion
     * @param typeStr StdString of type that you want check
     * @return whether InfoVersion has this type
     */
    @FFINameAlias("CheckType")
    boolean checkType(@CXXReference StdString typeStr);

    /**
     * get user define types
     * @return StdVector of StdString
     */
    @FFINameAlias("user_define_types")
    @FFITypeAlias("std::vector<std::string>")
    @CXXReference StdVector<StdString> userDefineTypes();
}
