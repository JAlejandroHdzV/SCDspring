(window.webpackJsonp=window.webpackJsonp||[]).push([[13],{dJ0T:function(e,t,i){"use strict";i.r(t),i.d(t,"QsolLicensesModule",(function(){return ie}));var s=i("2kYt"),a=i("40Bj"),o=i("nIj0"),r=i("m1yc"),n=i("PCNd"),l=i("sEIs"),d=i("OZ4H"),c=i("EM62"),b=i("ROBh"),h=i("jIqt"),p=i("YtkY"),u=i("ruxD"),m=i("IkLD"),g=i("qFEQ"),f=i("29Wa"),v=i("Cd2c"),y=i("ulve"),C=i("gcUQ"),D=i("mFH5");function x(e,t){if(1&e){const e=c.Tb();c.Sb(0,"mat-option",17),c.ac("onSelectionChange",(function(t){return c.sc(e),c.ec().spCodeChanged(t)})),c.Dc(1),c.Rb()}if(2&e){const e=t.$implicit;c.jc("value",e),c.Bb(1),c.Fc(" ",e.spCode," ")}}function F(e,t){1&e&&(c.Sb(0,"mat-error"),c.Dc(1," SP Code Duplicado "),c.Rb())}function R(e,t){if(1&e&&(c.Sb(0,"mat-error"),c.Dc(1),c.Rb()),2&e){const e=c.ec();c.Bb(1),c.Fc(" ",e.dictionary.translate("mandatoryField")," ")}}function S(e,t){if(1&e&&(c.Qb(0),c.Dc(1),c.Pb()),2&e){const e=c.ec();c.Bb(1),c.Fc("",e.dictionary.translate("mandatoryField")," ")}}function j(e,t){if(1&e&&(c.Qb(0),c.Dc(1),c.Pb()),2&e){const e=c.ec();c.Bb(1),c.Ec(e.dictionary.translate("lessThanDate"))}}let B=(()=>{class e{constructor(e,t,i,s,a,o){this.fb=e,this.fs=t,this.qls=i,this.sps=s,this.spCodeLookupService=a,this.changeDetectorRef=o,this.cummins=window.cummins,this.validDates=!0,this.disabledEvent=new c.o,this.optionsSp=[]}get spCode(){return this.mainForm.get("spCode")}get name(){return this.mainForm.get("name")}get distributor(){return this.mainForm.get("distributor")}get registerDate(){return this.mainForm.get("registerDate")}get expirationDate(){return this.mainForm.get("expirationDate")}ngOnInit(){this.isNew=!this.data,this.setupForm(),this.sps.getSpCodes().subscribe(e=>{this.optionsSp=[...e],this.filteredOptions=this.mainForm.controls.spCode.valueChanges.pipe(Object(h.a)(""),Object(p.a)(e=>"string"==typeof e?e:e.spCode),Object(p.a)(e=>e?this._filter(e):this.optionsSp.slice()))})}disableFields(){this.fs.disableFields(this.mainForm)}enableFields(){this.fs.enableFields(this.mainForm),this.isNew||(this.spCode.disable(),this.name.disable(),this.distributor.disable(),this.registerDate.disable(),this.expirationDate.disable())}submit(){const e=this.mainForm.value;let t;return console.log(e.spCode),t={spCode:e.spCode.spCode,fechaRegistro:e.registerDate.getTime(),fechaExpiracion:e.expirationDate.getTime(),del:e.del||"N"},this.isNew?this.qls.save(t):this.qls.update(t,e.spCode.spCode)}showSPCodeLabel(e){return null==e?void 0:e.value}displayFn(e){return e&&e.spCode?e.spCode:""}_filter(e){const t=e.toLowerCase();return this.optionsSp.filter(e=>e.spCode.toLowerCase().includes(t))}spCodeChanged(e){this.duplicatedRegionValidator.bind(this);const t=e.source.value;this.name.setValue(t.providerName),this.distributor.setValue(t.responsibleBranchCode+" - "+t.responsibleBranchName)}setupForm(){if(this.mainForm=this.fb.group({spCode:[null,[o.s.required],this.duplicatedRegionValidator.bind(this)],name:null,distributor:null,registerDate:[null,[o.s.required]],expirationDate:[null,[o.s.required]],del:null}),!this.isNew){this.spCode.disable();const e=this.getQsolLicensesCriteriaModel(this.data.spCodeObj.responsibleBranchCode);this.sps.findByCriteria(e).subscribe(e=>{this.mainForm.patchValue({registerDate:new Date(this.data.fechaRegistro),expirationDate:new Date(this.data.fechaExpiracion),del:this.data.del}),this.sps.getInfoSpcode(this.data.spCodeObj.spCode).subscribe(e=>{this.mainForm.patchValue({spCode:e[0],name:e[0].providerName,distributor:e[0].responsibleBranchCode+" - "+e[0].responsibleBranchName})})})}}getQsolLicensesCriteriaModel(e){return{spCode:e,providerName:null,responsibleBranchCode:null,idPais:null,idOem:null,add:null,city:null,iso:null,tipo:null,creationDate:null,createdBy:null,lastUpdateDate:null,lastUpdatedBy:null,del:"N"}}duplicatedRegionValidator(e){return"string"==typeof e.value?Object(b.a)({noMatch:!0}):"object"==typeof e.value?this.qls.findByCriteria({spCode:e.value.spCode,del:"N"}).pipe(Object(p.a)(e=>{var t,i;return console.log("R",0===e.length||1==e.length&&(null===(t=this.data)||void 0===t?void 0:t.spCode)==e[0].spCode?null:{duplicated:!0}),0===e.length||1==e.length&&(null===(i=this.data)||void 0===i?void 0:i.spCode)==e[0].spCode?null:{duplicated:!0}}),Object(u.a)(()=>this.changeDetectorRef.markForCheck())):this.qls.findByCriteria({spCode:e.value,del:"N"}).pipe(Object(p.a)(e=>{var t,i;return console.log("R",0===e.length||1==e.length&&(null===(t=this.data)||void 0===t?void 0:t.spCode)==e[0].spCode?null:{duplicated:!0}),0===e.length||1==e.length&&(null===(i=this.data)||void 0===i?void 0:i.spCode)==e[0].spCode?null:{duplicated:!0}}),Object(u.a)(()=>this.changeDetectorRef.markForCheck()))}}return e.\u0275fac=function(t){return new(t||e)(c.Mb(o.d),c.Mb(r.g),c.Mb(r.q),c.Mb(r.s),c.Mb(m.a),c.Mb(c.h))},e.\u0275cmp=c.Gb({type:e,selectors:[["infL-main-form"]],inputs:{data:"data",dictionary:"dictionary"},decls:37,vars:22,consts:[["autocomplete","off",3,"formGroup"],["fxLayout","row","fxLayout.xs","column"],["fxFlex","100%"],[1,"example-full-width"],["formControlName","spCode","matInput","","required","","type","text",3,"matAutocomplete","placeholder"],[3,"displayWith"],["auto","matAutocomplete"],[3,"value","onSelectionChange",4,"ngFor","ngForOf"],[4,"ngIf"],["formControlName","name","matInput","","readonly","",3,"placeholder"],["formControlName","distributor","matInput","","readonly","",3,"placeholder"],["fxFlex","50%"],["formControlName","registerDate","matInput","","readonly","",3,"matDatepicker","placeholder","max"],["matSuffix","",3,"for"],["registerDatepicker",""],["formControlName","expirationDate","matInput","","readonly","",3,"matDatepicker","placeholder","min"],["expirationDatepicker",""],[3,"value","onSelectionChange"]],template:function(e,t){if(1&e&&(c.Sb(0,"form",0),c.Sb(1,"div",1),c.Sb(2,"div",2),c.Sb(3,"mat-form-field",3),c.Nb(4,"input",4),c.Sb(5,"mat-autocomplete",5,6),c.Bc(7,x,2,2,"mat-option",7),c.fc(8,"async"),c.Rb(),c.Bc(9,F,2,0,"mat-error",8),c.Bc(10,R,2,1,"mat-error",8),c.Rb(),c.Rb(),c.Rb(),c.Sb(11,"div",1),c.Sb(12,"div",2),c.Sb(13,"mat-form-field"),c.Nb(14,"input",9),c.Rb(),c.Rb(),c.Rb(),c.Sb(15,"div",1),c.Sb(16,"div",2),c.Sb(17,"mat-form-field"),c.Nb(18,"input",10),c.Rb(),c.Rb(),c.Rb(),c.Sb(19,"div",1),c.Sb(20,"div",11),c.Sb(21,"mat-form-field"),c.Nb(22,"input",12),c.Nb(23,"mat-datepicker-toggle",13),c.Nb(24,"mat-datepicker",null,14),c.Sb(26,"mat-error"),c.Dc(27),c.Rb(),c.Rb(),c.Rb(),c.Sb(28,"div",11),c.Sb(29,"mat-form-field"),c.Nb(30,"input",15),c.Nb(31,"mat-datepicker-toggle",13),c.Nb(32,"mat-datepicker",null,16),c.Sb(34,"mat-error"),c.Bc(35,S,2,1,"ng-container",8),c.Bc(36,j,2,1,"ng-container",8),c.Rb(),c.Rb(),c.Rb(),c.Rb(),c.Rb()),2&e){const e=c.qc(6),i=c.qc(25),s=c.qc(33);c.jc("formGroup",t.mainForm),c.Bb(4),c.jc("matAutocomplete",e)("placeholder",t.dictionary.translate("spCode")),c.Bb(1),c.jc("displayWith",t.displayFn),c.Bb(2),c.jc("ngForOf",c.gc(8,20,t.filteredOptions)),c.Bb(2),c.jc("ngIf",t.spCode.invalid&&t.spCode.errors.duplicated),c.Bb(1),c.jc("ngIf",t.spCode.invalid&&(t.spCode.errors.required||t.spCode.errors.noMatch)),c.Bb(4),c.jc("placeholder",t.dictionary.translate("name")),c.Bb(4),c.jc("placeholder",t.dictionary.translate("distributor")),c.Bb(4),c.jc("matDatepicker",i)("placeholder",t.dictionary.translate("registerDate")+" *")("max",t.expirationDate.value),c.Bb(1),c.jc("for",i),c.Bb(4),c.Fc(" ",t.dictionary.translate("mandatoryField")," "),c.Bb(3),c.jc("matDatepicker",s)("placeholder",t.dictionary.translate("expirationDate")+" *")("min",t.registerDate.value),c.Bb(1),c.jc("for",s),c.Bb(4),c.jc("ngIf",t.mainForm.controls.expirationDate.invalid&&t.mainForm.controls.expirationDate.errors.required),c.Bb(1),c.jc("ngIf",t.mainForm.controls.expirationDate.invalid&&t.mainForm.controls.expirationDate.errors.lessThan)}},directives:[o.t,o.m,o.f,g.c,g.a,f.c,o.c,v.b,y.c,o.l,o.e,o.r,y.a,s.k,s.l,C.b,C.d,f.g,C.a,f.b,D.n],pipes:[s.b],styles:[".mat-form-field[_ngcontent-%COMP%]{min-inline-size:95%}"]}),e})(),L=(()=>{class e{constructor(e,t,i){this.data=e,this.dialogRef=t,this.message=i,this.loading=!1}ngOnInit(){this.title=this.data.dictionary.translate(this.data.qsolLicense?"editQsolLicense":"addQsolLicense")}onClose(){this.dialogRef.close()}onSubmit(){this.loading=!0,this.mfc.disableFields(),this.mfc.submit().subscribe(e=>{this.loading=!1,this.mfc.enableFields(),this.dialogRef.close({})},e=>{this.loading=!1,this.mfc.enableFields(),this.message.genericHttpError()})}}return e.\u0275fac=function(t){return new(t||e)(c.Mb(d.a),c.Mb(d.f),c.Mb(r.k))},e.\u0275cmp=c.Gb({type:e,selectors:[["infL-main-form-dialog"]],viewQuery:function(e,t){var i;1&e&&c.Hc(B,!0),2&e&&c.pc(i=c.bc())&&(t.mfc=i.first)},decls:2,vars:7,consts:[[3,"disabled","loading","title","submitText","cancelText","closeEvent","submitEvent"],[2,"width","200px",3,"data","dictionary"]],template:function(e,t){1&e&&(c.Sb(0,"shared-form-dialog",0),c.ac("closeEvent",(function(){return t.onClose()}))("submitEvent",(function(){return t.onSubmit()})),c.Nb(1,"infL-main-form",1),c.Rb()),2&e&&(c.jc("disabled",t.loading||(null==t.mfc?null:t.mfc.mainForm.pristine)||!(null!=t.mfc&&t.mfc.mainForm.valid))("loading",t.loading)("title",t.title)("submitText",t.data.dictionary.translate("save"))("cancelText",t.data.dictionary.translate("cancel")),c.Bb(1),c.jc("data",t.data.qsolLicense)("dictionary",t.data.dictionary))},directives:[r.f,B],styles:[""]}),e})();var w=i("DGrs"),E=i("8j5Y"),N=i("J+dc"),k=i("J0Eg");let M=(()=>{class e extends k.a{constructor(e,t){super(),this.qls=e,this.datePipe=t}loadData(e){let t;return t={spCode:null==e?void 0:e.spCode,fechaRegistro:null,fechaExpiracion:null,del:"N"},this.qls.findByCriteria(t).pipe(Object(E.a)(e=>{let t,i;for(let s=0;s<e.length;s++)t=new Date(e[s].fechaRegistro),i=new Date(e[s].fechaExpiracion),e[s].name=e[s].spCodeObj.providerName,e[s].distributor=e[s].spCodeObj.responsibleBranchCode,e[s].fechaRegistro=this.datePipe.transform(new Date(t),"MM-dd-yyyy"),e[s].fechaExpiracion=this.datePipe.transform(new Date(i),"MM-dd-yyyy");this.dataSource.replaceAll(e)}),Object(N.a)(1))}push(e){this.dataSource.push(e)}}return e.\u0275fac=function(t){return new(t||e)(c.Wb(r.q),c.Wb(s.e))},e.\u0275prov=c.Ib({token:e,factory:e.\u0275fac,providedIn:"root"}),e})();var O=i("+Tre"),q=i("PBFl");function I(e,t){1&e&&c.Nb(0,"shared-progress-bar")}let P=(()=>{class e{constructor(e,t,i,s){this.mts=e,this.message=t,this.dialog=i,this.qls=s,this.columns=T,this.loading=!1,this.operations=A,this.subscriptions=[],this.massLoadFile=[],this.eraseData=!1,this.massiveLoadErrors=[],this.eventListeners(),this.mts.loadData().subscribe(e=>{console.log("Data",e),this.dataSource=e})}ngOnInit(){for(let e=0;e<this.columns.length;e++)if(this.columns[e].label=this.dictionary.translate(T[e].label),"operations"==this.columns[e].key)for(let t=0;t<this.columns[e].value.length;t++)this.columns[e].value[t].label=this.dictionary.translate(T[e].value[t].label)}ngOnDestroy(){this.subscriptions.forEach(e=>e.unsubscribe())}onOperation(e){switch(e.operation){case"edit":this.onEdit(e.item);break;case"delete":this.onDelete(e.item)}}massLoadFunction(){this.loading=!0,this.qls.massiveLoad(this.massLoadFile[0].data,1==this.eraseData?"1":"0").pipe(Object(u.a)(()=>{this.mts.loadData().pipe(Object(u.a)(()=>this.loading=!1)).subscribe(e=>{this.dataSource=e}),this.openResults()})).subscribe(e=>{const t=[];e.forEach(e=>{(null==e?void 0:e.errores)&&(t.push("Registros Actualizados: "+e.registrosActualizados,"Registros Eliminados: "+e.registrosEliminados,"Registros Nuevos: "+e.registrosNuevos),t.push(...e.errores.map(e=>`Renglon: ${e.line} - ${e.errorType}`)))}),this.massiveLoadErrors=t})}onAdd(){this.dialog.open(L,{disableClose:!0,height:"400px",width:"600px",data:{dictionary:this.dictionary,qsolLicense:null}}).afterClosed().subscribe(e=>{e&&(this.mts.push(e),this.mts.loadData().subscribe(e=>{this.dataSource=e}),this.message.genericSaveMessage())})}onEdit(e){this.dialog.open(L,{disableClose:!0,height:"400px",width:"600px",data:{dictionary:this.dictionary,qsolLicense:e}}).afterClosed().subscribe(e=>{e&&(this.message.genericSaveMessage(),this.mts.loadData().subscribe(e=>{this.dataSource=e}))})}onDelete(e){this.loading=!0,this.qls.delete(e.spCode).subscribe(()=>{this.message.show("Licencia de Qsol Eliminada"),this.mts.loadData().subscribe(e=>{this.dataSource=e}),this.loading=!1},e=>{this.message.genericHttpError(),this.loading=!1})}eventListeners(){this.subscriptions.push(this.mts.dataEvent.subscribe(e=>this.operations.forEach(t=>t.disabled=0===e.length)))}openResults(){this.dialog.open(w.a,{disableClose:!1,height:"400px",width:"600px",data:{massiveLoadErrors:this.massiveLoadErrors}})}}return e.\u0275fac=function(t){return new(t||e)(c.Mb(M),c.Mb(r.k),c.Mb(d.b),c.Mb(r.q))},e.\u0275cmp=c.Gb({type:e,selectors:[["infL-main-table"]],inputs:{dictionary:"dictionary"},decls:15,vars:14,consts:[[4,"ngIf"],[1,"mat-elevation-z8","p1"],["fxLayout","row","fxLayout.xs","column"],["fxFlex","5%"],["fxFlex","20%"],["accept","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",3,"disabled","files","option","lengthFile"],["fxFlex","20%",2,"align-self","center"],[3,"ngModel","ngModelChange"],["fxFlex","50%",2,"align-self","center"],["mat-raised-button","",3,"disabled","click"],[3,"dictionary","columns","data","showPagination","operationEvent"],[3,"text","callback"]],template:function(e,t){1&e&&(c.Bc(0,I,1,0,"shared-progress-bar",0),c.Sb(1,"div",1),c.Sb(2,"div",2),c.Nb(3,"div",3),c.Sb(4,"div",4),c.Nb(5,"shared-file-upload-control",5),c.Rb(),c.Sb(6,"div",6),c.Sb(7,"mat-checkbox",7),c.ac("ngModelChange",(function(e){return t.eraseData=e})),c.Dc(8),c.Rb(),c.Rb(),c.Sb(9,"div",8),c.Sb(10,"button",9),c.ac("click",(function(){return t.massLoadFunction()})),c.Dc(11),c.Rb(),c.Rb(),c.Rb(),c.Rb(),c.Nb(12,"br"),c.Sb(13,"shared-search-table",10),c.ac("operationEvent",(function(e){return t.onOperation(e)})),c.Rb(),c.Sb(14,"shared-add-button",11),c.ac("callback",(function(){return t.onAdd()})),c.Rb()),2&e&&(c.jc("ngIf",t.loading),c.Bb(5),c.jc("disabled",!1)("files",t.massLoadFile)("option",t.dictionary.translate("fileUp"))("lengthFile",t.dictionary.translate("lengthFile")),c.Bb(2),c.jc("ngModel",t.eraseData),c.Bb(1),c.Fc(" ",t.dictionary.translate("eraseBefore")," "),c.Bb(2),c.jc("disabled",!t.massLoadFile[0]||t.loading),c.Bb(1),c.Fc(" ",t.dictionary.translate("massive")," "),c.Bb(2),c.jc("dictionary",t.dictionary)("columns",t.columns)("data",t.dataSource)("showPagination",!0),c.Bb(1),c.jc("text",t.dictionary.translate("addQsolLicense")))},directives:[s.l,g.c,g.a,r.e,O.a,o.l,o.o,q.b,r.v,r.a,r.o],styles:[""]}),e})();const T=[{key:"spCode",label:"spCode"},{key:"name",label:"name"},{key:"distributor",label:"distributor"},{key:"fechaRegistro",label:"registerDate"},{key:"fechaExpiracion",label:"expirationDate"},{key:"operations",label:"actions",type:r.b.Operations,value:[{label:"edit",matIcon:"edit",operation:"edit"},{label:"delete",matIcon:"delete",operation:"delete"}]}],A=[{disabled:!1,faIcon:"faFileExcel",label:"Exportar Reporte",operation:"export"}];function Q(e,t){1&e&&c.Nb(0,"shared-progress-bar")}function J(e,t){if(1&e&&(c.Sb(0,"div"),c.Bc(1,Q,1,0,"shared-progress-bar",0),c.Nb(2,"infL-main-table",1),c.Rb()),2&e){const e=c.ec();c.Bb(1),c.jc("ngIf",e.loading),c.Bb(1),c.jc("dictionary",e.dictionary)}}let G=(()=>{class e{constructor(e,t,i,s,a){this.dialog=e,this.message=t,this.mts=i,this.qls=s,this.tls=a,this.dictionary=new r.j,this.fields=H,this.loading=!1,this.massLoadFile=[],this.eraseData=!1,this.massiveLoadErrors=[],this.flag=!1}ngOnInit(){this.tls.getLenguaje().subscribe(e=>{this.dictionary.setLanguage(e.language),this.flag=!0})}massLoadFunction(){this.loading=!0,this.qls.massiveLoad(this.massLoadFile[0].data,1==this.eraseData?"1":"0").pipe(Object(u.a)(()=>{this.mts.loadData().pipe(Object(u.a)(()=>this.loading=!1)).subscribe(),this.openResults()})).subscribe(e=>{const t=[];e.forEach(e=>{(null==e?void 0:e.errores)&&(t.push("Registros Actualizados: "+e.registrosActualizados,"Registros Eliminados: "+e.registrosEliminados,"Registros Nuevos: "+e.registrosNuevos),t.push(...e.errores.map(e=>`Renglon: ${e.line} - ${e.errorType}`)))}),this.massiveLoadErrors=t})}onAdd(){this.dialog.open(L,{disableClose:!0,height:"400px",width:"600px",data:{dictionary:this.dictionary,qsolLicense:null}}).afterClosed().subscribe(e=>{e&&(this.mts.push(e),this.mts.loadData().subscribe(),this.message.genericSaveMessage())})}onSearch(e){return e?(this.loading=!0,this.mts.loadData(e).pipe(Object(u.a)(()=>this.loading=!1)).subscribe(()=>{},e=>this.message.genericHttpError(e))):this.mts.loadData().subscribe(),null}openResults(){this.dialog.open(w.a,{disableClose:!1,height:"400px",width:"600px",data:{massiveLoadErrors:this.massiveLoadErrors}})}}return e.\u0275fac=function(t){return new(t||e)(c.Mb(d.b),c.Mb(r.k),c.Mb(M),c.Mb(r.q),c.Mb(r.u))},e.\u0275cmp=c.Gb({type:e,selectors:[["infL-main-view"]],decls:1,vars:1,consts:[[4,"ngIf"],[3,"dictionary"]],template:function(e,t){1&e&&c.Bc(0,J,3,2,"div",0),2&e&&c.jc("ngIf",t.flag)},directives:[s.l,P,r.o],styles:[""]}),e})();const H=[{label:"SP Code",type:"text",value:"spCode"}],V=[{component:G,path:""}];let z=(()=>{class e{}return e.\u0275mod=c.Kb({type:e}),e.\u0275inj=c.Jb({factory:function(t){return new(t||e)},imports:[[l.a.forChild(V)],l.a]}),e})();var U=i("R7+U"),W=i("KZIX"),$=i("FlRo"),Y=i("cePI"),K=i("iAde"),_=i("k8N0"),Z=i("bFHC"),X=i("Jb3d"),ee=i("66mq"),te=i("csyo");let ie=(()=>{class e{}return e.\u0275mod=c.Kb({type:e}),e.\u0275inj=c.Jb({factory:function(t){return new(t||e)},providers:[s.e],imports:[[s.c,z,a.a,o.g,C.c,f.e,v.c,D.m,U.b,W.a,$.m,o.q,Y.c,K.b,_.a,r.t,n.a,q.c,d.e,Z.b,X.b,ee.b,te.b,y.b,O.b]]}),e})()}}]);