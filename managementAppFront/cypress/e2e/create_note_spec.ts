/// <reference types="cypress" />

describe('Create Note', () => {
    beforeEach(() => {
        cy.visit('/notes/create');
    });

    it('should create a new note and display it in the list', () => {
        cy.intercept('POST', '/api/notes').as('createNote');
        cy.intercept('GET', '/api/notes').as('getNotes');

        cy.get('input[formControlName="title"]').type('Test Note');
        cy.get('textarea[formControlName="description"]').type('This is a description for the test note.');
        cy.get('form').submit();

        cy.wait('@createNote').its('response.statusCode').should('eq', 201);
        cy.wait('@getNotes');

        cy.url().should('include', '/notes');
        cy.wait(2000);
        
        cy.contains('Test Note').should('exist');
    });
});