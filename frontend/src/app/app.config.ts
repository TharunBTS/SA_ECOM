// import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
// import { provideRouter } from '@angular/router';

// import { routes } from './app.routes';
// import { DemoAngularMaterailModule } from './DemoAngularMaterialModule';
// import { HttpClientModule } from '@angular/common/http';

// export const appConfig: ApplicationConfig = {
//   providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes),importProvidersFrom(HttpClientModule,DemoAngularMaterailModule)]
// };


import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http'; 
import { routes } from './app.routes';
import { importProvidersFrom } from '@angular/core';
import { DemoAngularMaterailModule } from './DemoAngularMaterialModule';

export const appConfig: ApplicationConfig = {
  providers: [
    
    provideRouter(routes),
    provideHttpClient(), 
    importProvidersFrom(DemoAngularMaterailModule)
  ]
};
