import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {RegisterComponent} from "./auth/register/register.component";
import {LoginComponent} from "./auth/login/login.component";
import {AnonymousAccessGuardService} from "./auth-guards/anonymous-access-guard.service";
import {AppsComponent} from "./apps/apps.component";
import {AppsListComponent} from "./apps/apps-list/apps-list.component";
import {KibanaComponent} from "./apps/kibana/kibana.component";
import {ElasticComponent} from "./apps/elastic/elastic.component";
import {GrokComponent} from "./apps/grok/grok.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {path: '', component: AppsListComponent},
            {path: 'apps', component: AppsListComponent},
            {path: 'appsadd', component: AppsComponent},
            {path: 'kibana', component: KibanaComponent},
            {path: 'elastic',component:ElasticComponent},
            {path: 'grok',component:GrokComponent},
            {path: 'register', component: RegisterComponent, canActivate: [AnonymousAccessGuardService]},
            {path: 'login', component: LoginComponent, canActivate: [AnonymousAccessGuardService]},
            {path: '**', component: PageNotFoundComponent}
        ])
    ],
    providers: [AnonymousAccessGuardService],
    exports: [RouterModule]
})
export class AppRoutingModule {
}