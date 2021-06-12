import { ViewVerificationReqComponent } from './pages/admin-homepage/view-verification-req/view-verification-req.component';
import { NewVerificationRequestComponent } from './pages/homepage/new-verification-request/new-verification-request.component';
import { AdminHomepageComponent } from './pages/admin-homepage/admin-homepage.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { ChangePasswordComponent } from './pages/change-password/change-password.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { LoginComponent } from './pages/login/login.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoNgZorroAntdModule } from './ng-zorro-antd.module';
import { ToastrModule } from 'ngx-toastr';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NewPostComponent } from './pages/new-post/new-post.component';
import { HomepageComponent } from './pages/homepage/homepage.component';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    ChangePasswordComponent,
    ResetPasswordComponent,
    HomePageComponent,
    NewPostComponent,
    HomepageComponent,
    AdminHomepageComponent,
    NewVerificationRequestComponent,
    ViewVerificationReqComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NzTableModule,
    ToastrModule.forRoot()
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
