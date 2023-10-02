package com.mac.m2land_cms.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;


/**
 * The class AutowireCapableBeanJobFactory
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Component
public class AutowireCapableBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    private ApplicationContext applicationContext;

    public AutowireCapableBeanJobFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
