import { ApplicationRef, DoBootstrap, Injector, NgModule } from '@angular/core';
import { createCustomElement } from '@angular/elements';
import { BrowserModule } from '@angular/platform-browser';

import { PersonModule } from './person/person.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {PersonComponent} from "./person/person.component";

@NgModule({
    imports: [
        BrowserModule,
        PersonModule,
        BrowserAnimationsModule
    ],
    providers: [],
})
export class ElementModule implements DoBootstrap {

    constructor(
        private injector: Injector
    ) {}

    ngDoBootstrap(appRef: ApplicationRef) {
        if (!customElements.get('person-provider')) {
            // Register only if 'person-provider' entry is not found in the registry

            // Step 3: personComponent stores the constructor class
            const personComponent = createCustomElement(PersonComponent, {
                injector: this.injector,    // This injector is used to load the component's factory
            });

            // Step 4: Registering custom tag 'person-provider' with the obtained custom class
            customElements.define('person-provider', personComponent);
        }
    }
}
