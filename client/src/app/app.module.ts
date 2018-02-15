import { HttpModule } from "@angular/http";
import {BrowserModule} from '@angular/platform-browser';
import {NgModule, ErrorHandler} from '@angular/core';
import {IonicApp, IonicModule, IonicErrorHandler} from 'ionic-angular';
import {MyApp} from './app.component';
import {Home} from '../pages/home/home';
import {Spot} from '../pages/spot/spot';
import {City} from '../pages/city/city';
import {Tabs} from '../pages/tabs/tabs';
import {Region} from '../pages/region/region';
import { Monuments } from './../pages/monuments/monuments';
import { Monument } from './../pages/monument/monument';
import {Favorie} from '../pages/favorie/favorie';
import {Circuit, AddCircuitModal} from '../pages/circuit/circuit';
import {StatusBar} from '@ionic-native/status-bar';
import {SplashScreen} from '@ionic-native/splash-screen';
import {Login} from "../pages/login/login";
import {Signup} from "../pages/signup/signup";
import {CustomFormsModule} from 'ng2-validation';
import {Storage, IonicStorageModule} from "@ionic/storage";
import {AuthenticationService} from "../services/authentication";
import {MonumentService} from "../services/monument.service";
import {CircuitService} from "../services/circuit.service";
import {HttpClientModule} from "@angular/common/http";
import {JWT_OPTIONS, JwtModule} from '@auth0/angular-jwt';
import { Utility } from '../providers/utility';

export function jwtOptionsFactory(storage: Storage) {
  return {
    tokenGetter: () => storage.get('jwt_token'),
    whitelistedDomains: ['localhost:5000']
  }
}


@NgModule({
  declarations: [
    MyApp,
    Home,
    Spot,
    City,
    Region,
    Monuments,
    Monument,
    Favorie,
    Circuit,
    AddCircuitModal,
    Tabs,
    Login,
    Signup
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    JwtModule.forRoot({
      jwtOptionsProvider: {
        provide: JWT_OPTIONS,
        useFactory: jwtOptionsFactory,
        deps: [Storage]
      }
    }),
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot(),
    CustomFormsModule,
    HttpModule,
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    Home,
    Spot,
    City,
    Region,
    Monuments,
    Monument,
    Tabs,
    Favorie,
    Circuit,
    AddCircuitModal,
    Login,
    Signup
  ],
  providers: [
    StatusBar,
    SplashScreen,
    Utility,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AuthenticationService,
    MonumentService,
    CircuitService
  ]
})
export class AppModule {
}
