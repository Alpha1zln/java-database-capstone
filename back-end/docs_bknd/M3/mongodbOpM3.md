

# Windows PowerShell
Copyright (C) Microsoft Corporation. All rights reserved.

PS C:\Users\shreyasHm> mongosh
Current Mongosh Log ID: 6b01f2348f70ac42335d65b6
Connecting to:          mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.14.3
Using MongoDB:          9.0.8
Using Mongosh:          2.14.3

For mongosh info see: https://www.mongodb.com/docs/mongodb-shell/


To help improve our products, pseudo-anonymous usage data is collected from interactive and agentic sessions only, and sent to MongoDB periodically (https://www.mongodb.com/legal/privacy-policy).
You can opt-out by running the disableTelemetry() command.

------
   The server generated these startup warnings when booting
   2026-11-21T20:17:49.587+05:30: Access control is not enabled for the database. Read and write access to data and configuration is unrestricted
------

test> show dbs;
admin           40.00 KiB
config         108.00 KiB
local           40.00 KiB
prescriptions   40.00 KiB
test> use prescriptions
switched to db prescriptions
prescriptions> db
prescriptions
prescriptions> show collections
prescriptions
prescriptions> db.prescriptions.find().pretty()
[
  {
    _id: ObjectId('6807dd712725f013281e7201'),
    patientName: 'John Smith',
    appointmentId: 51,
    medication: 'Paracetamol',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 6 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7202'),
    patientName: 'Emily Rose',
    appointmentId: 52,
    medication: 'Aspirin',
    dosage: '300mg',
    doctorNotes: 'Take 1 tablet after meals.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7203'),
    patientName: 'Michael Jordan',
    appointmentId: 53,
    medication: 'Ibuprofen',
    dosage: '400mg',
    doctorNotes: 'Take 1 tablet every 8 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7204'),
    patientName: 'Olivia Moon',
    appointmentId: 54,
    medication: 'Antihistamine',
    dosage: '10mg',
    doctorNotes: 'Take 1 tablet daily before bed.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7205'),
    patientName: 'Liam King',
    appointmentId: 55,
    medication: 'Vitamin C',
    dosage: '1000mg',
    doctorNotes: 'Take 1 tablet daily.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7206'),
    patientName: 'Sophia Lane',
    appointmentId: 56,
    medication: 'Antibiotics',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 12 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7207'),
    patientName: 'Noah Brooks',
    appointmentId: 57,
    medication: 'Paracetamol',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 6 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7208'),
    patientName: 'Ava Daniels',
    appointmentId: 58,
    medication: 'Ibuprofen',
    dosage: '200mg',
    doctorNotes: 'Take 1 tablet every 8 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7209'),
    patientName: 'William Harris',
    appointmentId: 59,
    medication: 'Aspirin',
    dosage: '300mg',
    doctorNotes: 'Take 1 tablet after meals.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7210'),
    patientName: 'Mia Green',
    appointmentId: 60,
    medication: 'Vitamin D',
    dosage: '1000 IU',
    doctorNotes: 'Take 1 tablet daily with food.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7211'),
    patientName: 'James Brown',
    appointmentId: 61,
    medication: 'Antihistamine',
    dosage: '10mg',
    doctorNotes: 'Take 1 tablet every morning.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7212'),
    patientName: 'Amelia Clark',
    appointmentId: 62,
    medication: 'Paracetamol',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 6 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7213'),
    patientName: 'Ben Johnson',
    appointmentId: 63,
    medication: 'Ibuprofen',
    dosage: '400mg',
    doctorNotes: 'Take 1 tablet every 8 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7214'),
    patientName: 'Ella Monroe',
    appointmentId: 64,
    medication: 'Vitamin C',
    dosage: '1000mg',
    doctorNotes: 'Take 1 tablet daily.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7215'),
    patientName: 'Lucas Turner',
    appointmentId: 65,
    medication: 'Aspirin',
    dosage: '300mg',
    doctorNotes: 'Take 1 tablet after meals.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7216'),
    patientName: 'Grace Scott',
    appointmentId: 66,
    medication: 'Paracetamol',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 6 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7217'),
    patientName: 'Ethan Hill',
    appointmentId: 67,
    medication: 'Ibuprofen',
    dosage: '400mg',
    doctorNotes: 'Take 1 tablet every 8 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7218'),
    patientName: 'Ruby Ward',
    appointmentId: 68,
    medication: 'Vitamin D',
    dosage: '1000 IU',
    doctorNotes: 'Take 1 tablet daily with food.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7219'),
    patientName: 'Jack Baker',
    appointmentId: 69,
    medication: 'Antibiotics',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 12 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7220'),
    patientName: 'Mia Hall',
    appointmentId: 70,
    medication: 'Paracetamol',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 6 hours.',
    _class: 'com.project.back_end.models.Prescription'
  }
]
Type "it" for more
prescriptions> db.prescriptions.findOne()
{
  _id: ObjectId('6807dd712725f013281e7201'),
  patientName: 'John Smith',
  appointmentId: 51,
  medication: 'Paracetamol',
  dosage: '500mg',
  doctorNotes: 'Take 1 tablet every 6 hours.',
  _class: 'com.project.back_end.models.Prescription'
}
prescriptions> db.prescriptions.countDocuments()
24
prescriptions> db.prescriptions.find({ patientName: "John Smith" })
[
  {
    _id: ObjectId('6807dd712725f013281e7201'),
    patientName: 'John Smith',
    appointmentId: 51,
    medication: 'Paracetamol',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 6 hours.',
    _class: 'com.project.back_end.models.Prescription'
  }
]
prescriptions>


---------


prescriptions> db.prescriptions.find().limit(5).pretty();
[
  {
    _id: ObjectId('6807dd712725f013281e7201'),
    patientName: 'John Smith',
    appointmentId: 51,
    medication: 'Paracetamol',
    dosage: '500mg',
    doctorNotes: 'Take 1 tablet every 6 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7202'),
    patientName: 'Emily Rose',
    appointmentId: 52,
    medication: 'Aspirin',
    dosage: '300mg',
    doctorNotes: 'Take 1 tablet after meals.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7203'),
    patientName: 'Michael Jordan',
    appointmentId: 53,
    medication: 'Ibuprofen',
    dosage: '400mg',
    doctorNotes: 'Take 1 tablet every 8 hours.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7204'),
    patientName: 'Olivia Moon',
    appointmentId: 54,
    medication: 'Antihistamine',
    dosage: '10mg',
    doctorNotes: 'Take 1 tablet daily before bed.',
    _class: 'com.project.back_end.models.Prescription'
  },
  {
    _id: ObjectId('6807dd712725f013281e7205'),
    patientName: 'Liam King',
    appointmentId: 55,
    medication: 'Vitamin C',
    dosage: '1000mg',
    doctorNotes: 'Take 1 tablet daily.',
    _class: 'com.project.back_end.models.Prescription'
  }
]
prescriptions>



-------------------------------------------
-------------------------------------------