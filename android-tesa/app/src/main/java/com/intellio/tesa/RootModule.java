package com.intellio.tesa;

import dagger.Module;

/**
 * Add all the other modules to this one.
 */
@Module(
        includes = {
                AndroidModule.class,
                TesaModule.class
        }
)
public class RootModule {
}
