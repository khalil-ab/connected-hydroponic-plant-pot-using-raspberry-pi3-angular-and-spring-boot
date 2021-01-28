import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import { MessageBoxComponent } from 'src/app/tsc-ui/message-box.component';
import { MessageBoxButtons } from 'src/message-box-buttons.enum';
import { MessageBoxModel } from 'src/message-box-model';
import { MessageBoxResult } from 'src/message-box-result.enum';

@Injectable({
  providedIn: 'root'
})
export class MessageBoxService {

  constructor(private dialog: MatDialog) { }

  show(
    title: string,
    message: string,
    buttons: MessageBoxButtons = MessageBoxButtons.ok,
    resultCallback: ((result: MessageBoxResult) => void) = undefined): void {

    this.dialog.open(MessageBoxComponent, { data: new MessageBoxModel(title, message, buttons, resultCallback) });
  }

}
