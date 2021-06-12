import { ViewVerificationReqComponent } from './pages/admin-homepage/view-verification-req/view-verification-req.component';
import { AdminHomepageComponent } from './pages/admin-homepage/admin-homepage.component';
import { NewVerificationRequestComponent } from './pages/homepage/new-verification-request/new-verification-request.component';
import { ChangePasswordComponent } from './pages/change-password/change-password.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { NewPostComponent } from './pages/new-post/new-post.component';

const routes: Routes = [
  { path: '', pathMatch:'full', redirectTo:'login'},
  { path: 'login', component: LoginComponent},
  { path: 'registration', component: RegistrationComponent},
  { path: 'homepage', component: HomepageComponent, children: [
    { path: 'reset-password/:id', component: ResetPasswordComponent},
    { path: 'change-password/:id', component: ChangePasswordComponent},
    { path: 'new-post',component:NewPostComponent},
    { path: 'new-verification-request',component:NewVerificationRequestComponent}
  ]}, 
  { path: 'admin', component:AdminHomepageComponent, children:[
  { path: 'viewVerifReq', component:ViewVerificationReqComponent}
  ]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
