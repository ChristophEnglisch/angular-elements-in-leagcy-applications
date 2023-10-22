import {Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PersonDto, NewPerson } from "../shared/PersonDto";
import { PersonService } from "./person.service";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.scss', '../../styles.scss'],
  encapsulation: ViewEncapsulation.ShadowDom,
})
export class PersonComponent implements OnInit {
  public persons: PersonDto[] = [];
  displayedColumns: string[] = ['firstname', 'lastname', 'age', 'edit', 'delete'];
  newPerson: NewPerson = { firstname: '', lastname: '', age: 0 };
  editingPersonId: number | null = null;

  @ViewChild('personDialog', { static: true }) personDialog!: TemplateRef<any>;
  @ViewChild('deleteConfirmationDialog', { static: true }) deleteConfirmationDialog!: TemplateRef<any>;
  private personToDelete: number | null = null;

  constructor(private dialog: MatDialog, private personService: PersonService) {}

  ngOnInit(): void {
    this.fetchPersons();
  }

  fetchPersons() {
    this.personService.getPersons().subscribe(data => {
      this.persons = data;
    });
  }

  openDialog(person?: PersonDto) {
    this.newPerson = { firstname: '', lastname: '', age: 0 };
    this.editingPersonId = null;

    if (person) {
      this.newPerson = { firstname: person.firstname, lastname: person.lastname, age: person.age };
      this.editingPersonId = person.id;
    }

    this.dialog.open(this.personDialog);
  }

  closeDialog() {
    this.dialog.closeAll();
  }

  addOrEditPerson() {
    if (this.editingPersonId !== null) {
      this.personService.updatePerson(this.editingPersonId, this.newPerson).subscribe(() => {
        this.fetchPersons();
        this.closeDialog();
      });
    } else {
      this.personService.addPerson(this.newPerson).subscribe(() => {
        this.fetchPersons();
        this.closeDialog();
      });
    }
  }

  deletePerson(id: number) {
    this.personToDelete = id;
    this.dialog.open(this.deleteConfirmationDialog);
  }

  confirmDelete() {
    if (this.personToDelete !== null) {
      this.personService.deletePerson(this.personToDelete).subscribe(() => {
        this.fetchPersons();
        this.closeDialog();
      });
      this.personToDelete = null;
    }
  }
}
