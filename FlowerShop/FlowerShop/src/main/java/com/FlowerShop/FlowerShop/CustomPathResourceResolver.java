package com.FlowerShop.FlowerShop;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolver;
import java.io.IOException;

public class CustomPathResourceResolver extends PathResourceResolver implements ResourceResolver {

    @Override
    protected Resource getResource(String resourcePath, Resource location) throws IOException {
        //fixes problems with whitespaces in url
        resourcePath = resourcePath.replace(" ","%20");
        return super.getResource(resourcePath, location);
    }
}
