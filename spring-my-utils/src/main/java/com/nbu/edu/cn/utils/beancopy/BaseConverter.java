package com.nbu.edu.cn.utils.beancopy;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class BaseConverter {

    private static final MapperFactory mapperFactory ;
    private static final ConverterFactory converterFactory;

    static{
        mapperFactory = new DefaultMapperFactory.Builder().build();
        converterFactory = mapperFactory.getConverterFactory();
    }

    public static <S,D> void registerFields(final Class<S> sourceClazz, final Class<D> destClazz, List<CopyTriplet<String,String,String>> copyTriplets){
        ClassMapBuilder<S,D> classMapBuilder = mapperFactory.classMap(SimpleBeanCopyUtils.getType(sourceClazz), SimpleBeanCopyUtils.getType(destClazz));
        if(CollectionUtils.isEmpty(copyTriplets)){
            return;
        }
        copyTriplets.forEach(copyTriplet -> {
            if(StringUtils.isNotBlank(copyTriplet.getConverterId())){
                classMapBuilder.fieldMap(copyTriplet.getSourceField(), copyTriplet.getTargetField()).converter(copyTriplet.getConverterId()).add();
            }else{
                classMapBuilder.fieldMap(copyTriplet.getSourceField(), copyTriplet.getTargetField());
            }
        });
        classMapBuilder.byDefault().register();
    }


    public static void registerFieldConverter(Map<String, CustomConverter> converterMap){
        if(MapUtils.isNotEmpty(converterMap)){
            converterMap.forEach(converterFactory::registerConverter);
        }
    }

    public static MapperFactory getMapperFactory() {
        return mapperFactory;
    }
}
