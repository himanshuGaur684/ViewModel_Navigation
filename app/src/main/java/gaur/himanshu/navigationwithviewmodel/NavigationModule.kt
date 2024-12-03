package gaur.himanshu.navigationwithviewmodel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator():Navigator{
        return NavigatorImpl()
    }

}