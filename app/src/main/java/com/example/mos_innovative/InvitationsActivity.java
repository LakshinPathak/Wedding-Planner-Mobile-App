package com.example.mos_innovative;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Typeface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class InvitationsActivity extends AppCompatActivity {

    private TextView invitationTextView;
    private EditText guestNameEditText;
    private ImageView brideImageView, groomImageView;
    private String brideName;
    private String groomName;

    private String selectedImageType; // To store whether bride or groom image is selected
    private boolean isBrideImageSelected = false; // Flag to check if bride image is selected
    private boolean isGroomImageSelected = false; // Flag to check if groom image is selected

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitations);

        // Get bride's and groom's names from intent
        brideName = getIntent().getStringExtra("BRIDE_NAME");
        groomName = getIntent().getStringExtra("GROOM_NAME");

        // Initialize UI elements
        invitationTextView = findViewById(R.id.invitationTextView);
        guestNameEditText = findViewById(R.id.guestNameEditText);
        brideImageView = findViewById(R.id.brideImageView);
        groomImageView = findViewById(R.id.groomImageView);

        Button sendInvitationButton = findViewById(R.id.sendInvitationButton);
        Button selectBrideImageButton = findViewById(R.id.selectBrideImageButton);
        Button selectGroomImageButton = findViewById(R.id.selectGroomImageButton);

        // Set up onClickListener for send invitation button
        sendInvitationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guestName = guestNameEditText.getText().toString();

                // Check if both images are selected
                if (isBrideImageSelected && isGroomImageSelected) {
                    String invitationMessage = generateInvitationMessage(guestName);
                    displayInvitation(invitationMessage);
                } else {
                    Toast.makeText(InvitationsActivity.this, "Please select both images before sending the invitation.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up onClickListener for selecting bride image
        selectBrideImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageType = "bride"; // Set the selected image type
                openImageChooser();
            }
        });

        // Set up onClickListener for selecting groom image
        selectGroomImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageType = "groom"; // Set the selected image type
                openImageChooser();
            }
        });
    }

    // Method to generate invitation message
    private String generateInvitationMessage(String guestName) {
        return "Dear " + guestName + ",\n\n" +
                "You are cordially invited to the wedding of " + brideName + " and " + groomName + ".\n" +
                "We look forward to celebrating this special day with you!\n\n" +
                "Best Regards,\n" +
                "The Wedding Planner";
    }

    // Method to open image chooser
    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                // Check which image is being set
                if (selectedImageType.equals("bride")) {
                    brideImageView.setImageURI(imageUri);
                    isBrideImageSelected = true; // Set the flag to true
                } else {
                    groomImageView.setImageURI(imageUri);
                    isGroomImageSelected = true; // Set the flag to true
                }
            }
        }
    }

    // Method to create a PDF of the invitation
//    private void createPdf(String invitationMessage) {
//        // Create a new PdfDocument
//        PdfDocument pdfDocument = new PdfDocument();
//
//        // Create a page description
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
//
//        // Start a new page
//        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//
//        // Get the canvas of the page
//        Canvas canvas = page.getCanvas();
//
//        // Set up paint for text
//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(12);
//
//        // Draw the invitation message on the canvas
//        canvas.drawText(invitationMessage, 10, 25, paint);
//
//        // Draw the bride and groom images (if selected)
//        if (isBrideImageSelected) {
//            Bitmap brideBitmap = ((BitmapDrawable) brideImageView.getDrawable()).getBitmap();
//            canvas.drawBitmap(brideBitmap, 10, 50, paint);
//        }
//
//        if (isGroomImageSelected) {
//            Bitmap groomBitmap = ((BitmapDrawable) groomImageView.getDrawable()).getBitmap();
//            canvas.drawBitmap(groomBitmap, 150, 50, paint);
//        }
//
//        // Finish the page
//        pdfDocument.finishPage(page);
//
//        // Save the document to the device storage
//        String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//        File file = new File(directoryPath, "WeddingInvitation.pdf");
//
//        try {
//            pdfDocument.writeTo(new FileOutputStream(file));
//            Toast.makeText(this, "PDF saved to Downloads folder!", Toast.LENGTH_LONG).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Error saving PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//        // Close the document
//        pdfDocument.close();
//    }

//    private void createPdf(String invitationMessage) {
//        // Create a new PdfDocument
//        PdfDocument pdfDocument = new PdfDocument();
//
//        // Create a page description with a larger page size for better design flexibility
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(400, 700, 1).create();
//
//        // Start a new page
//        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//
//        // Get the canvas of the page
//        Canvas canvas = page.getCanvas();
//
//        // Set up paint for text
//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//
//        // Set a title with larger text size and centered alignment
//        paint.setTextSize(24);
//        paint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText("You're Invited!", pageInfo.getPageWidth() / 2, 40, paint);  // Title
//
//        // Add a space before the next text block
//        paint.setTextSize(14);
//        paint.setTextAlign(Paint.Align.LEFT);
//
//        // Draw the invitation message with line breaks for proper formatting
//        String[] lines = invitationMessage.split("\n");
//        int yPosition = 80; // Starting y position for text
//        for (String line : lines) {
//            canvas.drawText(line, 10, yPosition, paint);
//            yPosition += 20; // Add line spacing
//        }
//
//        // Rescale bride and groom images before drawing them on the canvas
//        if (isBrideImageSelected) {
//            Bitmap brideBitmap = ((BitmapDrawable) brideImageView.getDrawable()).getBitmap();
//            Bitmap scaledBrideBitmap = Bitmap.createScaledBitmap(brideBitmap, 100, 100, false);
//            canvas.drawBitmap(scaledBrideBitmap, 30, yPosition + 20, paint); // Position for bride image
//        }
//
//        if (isGroomImageSelected) {
//            Bitmap groomBitmap = ((BitmapDrawable) groomImageView.getDrawable()).getBitmap();
//            Bitmap scaledGroomBitmap = Bitmap.createScaledBitmap(groomBitmap, 100, 100, false);
//            canvas.drawBitmap(scaledGroomBitmap, 250, yPosition + 20, paint); // Position for groom image
//        }
//
//        // Add a decorative border (optional)
//        Paint borderPaint = new Paint();
//        borderPaint.setColor(Color.DKGRAY);
//        borderPaint.setStyle(Paint.Style.STROKE);
//        borderPaint.setStrokeWidth(4);
//        canvas.drawRect(10, 10, pageInfo.getPageWidth() - 10, pageInfo.getPageHeight() - 10, borderPaint);
//
//        // Finish the page
//        pdfDocument.finishPage(page);
//
//        // Save the document to the device storage
//        String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//        File file = new File(directoryPath, "WeddingInvitation.pdf");
//
//        try {
//            pdfDocument.writeTo(new FileOutputStream(file));
//            Toast.makeText(this, "PDF saved to Downloads folder!", Toast.LENGTH_LONG).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Error saving PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//        // Close the document
//        pdfDocument.close();
//    }
//private void createPdf(String invitationMessage) {
//    // Create a new PdfDocument
//    PdfDocument pdfDocument = new PdfDocument();
//
//    // Create a page description with a larger page size for better design flexibility
//    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(400, 700, 1).create();
//
//    // Start a new page
//    PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//
//    // Get the canvas of the page
//    Canvas canvas = page.getCanvas();
//
//    // Set a colorful background
//    Paint backgroundPaint = new Paint();
//    backgroundPaint.setColor(Color.parseColor("#FFFACD")); // Soft yellow background
//    canvas.drawRect(0, 0, pageInfo.getPageWidth(), pageInfo.getPageHeight(), backgroundPaint);
//
//    // Set up paint for the title
//    Paint titlePaint = new Paint();
//    titlePaint.setColor(Color.parseColor("#FF6347")); // Tomato color for the title
//    titlePaint.setTextSize(30);
//    titlePaint.setTextAlign(Paint.Align.CENTER);
//    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
//
//    // Draw the title in the center at the top
//    canvas.drawText("You're Invited!", pageInfo.getPageWidth() / 2, 60, titlePaint); // Title
//
//    // Set up paint for body text
//    Paint textPaint = new Paint();
//    textPaint.setColor(Color.BLACK);
//    textPaint.setTextSize(14);
//    textPaint.setTextAlign(Paint.Align.LEFT);
//
//    // Draw the invitation message with line breaks for proper formatting
//    String[] lines = invitationMessage.split("\n");
//    int yPosition = 100; // Starting y position for text
//    for (String line : lines) {
//        canvas.drawText(line, 40, yPosition, textPaint); // Left margin of 40
//        yPosition += 20; // Add line spacing
//    }
//
//    // Set up bride and groom images side by side and center them below the text
//    if (isBrideImageSelected && isGroomImageSelected) {
//        // Rescale bride and groom images
//        Bitmap brideBitmap = ((BitmapDrawable) brideImageView.getDrawable()).getBitmap();
//        Bitmap groomBitmap = ((BitmapDrawable) groomImageView.getDrawable()).getBitmap();
//        Bitmap scaledBrideBitmap = Bitmap.createScaledBitmap(brideBitmap, 120, 120, false);
//        Bitmap scaledGroomBitmap = Bitmap.createScaledBitmap(groomBitmap, 120, 120, false);
//
//        // Calculate positions to center both images side by side
//        int imageStartX = (pageInfo.getPageWidth() - (scaledBrideBitmap.getWidth() + scaledGroomBitmap.getWidth() + 20)) / 2;
//        int imageY = yPosition + 40; // Adjust Y position after the text
//
//        // Draw bride and groom images side by side
//        canvas.drawBitmap(scaledBrideBitmap, imageStartX, imageY, textPaint);
//        canvas.drawBitmap(scaledGroomBitmap, imageStartX + scaledBrideBitmap.getWidth() + 20, imageY, textPaint); // 20 pixels space between images
//    }
//
//    // Add a decorative border around the entire page
//    Paint borderPaint = new Paint();
//    borderPaint.setColor(Color.parseColor("#4682B4")); // Steel blue border
//    borderPaint.setStyle(Paint.Style.STROKE);
//    borderPaint.setStrokeWidth(6);
//    canvas.drawRect(10, 10, pageInfo.getPageWidth() - 10, pageInfo.getPageHeight() - 10, borderPaint);
//
//    // Finish the page
//    pdfDocument.finishPage(page);
//
//    // Save the document to the device storage
//    String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//    File file = new File(directoryPath, "WeddingInvitation.pdf");
//
//    try {
//        pdfDocument.writeTo(new FileOutputStream(file));
//        Toast.makeText(this, "PDF saved to Downloads folder!", Toast.LENGTH_LONG).show();
//    } catch (IOException e) {
//        e.printStackTrace();
//        Toast.makeText(this, "Error saving PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
//    }
//
//    // Close the document
//    pdfDocument.close();
//}
//private void createPdf(String invitationMessage) {
//    // Create a new PdfDocument
//    PdfDocument pdfDocument = new PdfDocument();
//
//    // Create a page description
//    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(500, 700, 1).create();
//
//    // Start a new page
//    PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//
//    // Get the canvas of the page
//    Canvas canvas = page.getCanvas();
//
//    // Set up paint for the background
//    Paint backgroundPaint = new Paint();
//    backgroundPaint.setColor(Color.parseColor("#FFB6C1")); // Light pink background
//    canvas.drawRect(0, 0, pageInfo.getPageWidth(), pageInfo.getPageHeight(), backgroundPaint);
//
//    // Set up paint for title text
//    Paint titlePaint = new Paint();
//    titlePaint.setColor(Color.BLACK);
//    titlePaint.setTextSize(30);
//    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
//    titlePaint.setTextAlign(Paint.Align.CENTER);
//
//    // Draw the title
//    canvas.drawText("You're Invited!", pageInfo.getPageWidth() / 2, 50, titlePaint);
//
//    // Set up paint for the invitation message
//    Paint messagePaint = new Paint();
//    messagePaint.setColor(Color.BLACK);
//    messagePaint.setTextSize(20);
//    messagePaint.setTextAlign(Paint.Align.LEFT);
//
//    // Set line spacing
//    messagePaint.setTextSize(20);
//    String[] lines = invitationMessage.split("\n");
//    float lineHeight = messagePaint.getTextSize() + 10;
//
//    // Draw the invitation message
//    float y = 100; // Starting Y position for the text
//    for (String line : lines) {
//        canvas.drawText(line, 30, y, messagePaint);
//        y += lineHeight; // Move to the next line
//    }
//
//    // Draw bride image if selected
//    if (isBrideImageSelected) {
//        Bitmap brideBitmap = ((BitmapDrawable) brideImageView.getDrawable()).getBitmap();
//        Bitmap scaledBrideBitmap = Bitmap.createScaledBitmap(brideBitmap, 150, 150, false);
//        canvas.drawBitmap(scaledBrideBitmap, (pageInfo.getPageWidth() / 4) - (scaledBrideBitmap.getWidth() / 2), y + 20, null);
//    }
//
//    // Draw groom image if selected
//    if (isGroomImageSelected) {
//        Bitmap groomBitmap = ((BitmapDrawable) groomImageView.getDrawable()).getBitmap();
//        Bitmap scaledGroomBitmap = Bitmap.createScaledBitmap(groomBitmap, 150, 150, false);
//        canvas.drawBitmap(scaledGroomBitmap, (3 * pageInfo.getPageWidth() / 4) - (scaledGroomBitmap.getWidth() / 2), y + 20, null);
//    }
//
//    // Draw a decorative line
//    Paint linePaint = new Paint();
//    linePaint.setColor(Color.WHITE);
//    linePaint.setStrokeWidth(5);
//    canvas.drawLine(30, y + 200, pageInfo.getPageWidth() - 30, y + 200, linePaint);
//
//    // Additional message
//    Paint additionalMessagePaint = new Paint();
//    additionalMessagePaint.setColor(Color.BLACK);
//    additionalMessagePaint.setTextSize(18);
//    additionalMessagePaint.setTextAlign(Paint.Align.CENTER);
//
//    String additionalMessage = "Join us for a celebration of love, laughter, and happily ever after.";
//    canvas.drawText(additionalMessage, pageInfo.getPageWidth() / 2, y + 240, additionalMessagePaint);
//
//    // Finish the page
//    pdfDocument.finishPage(page);
//
//    // Save the document to the device storage
//    String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//    File file = new File(directoryPath, "WeddingInvitation.pdf");
//
//    try {
//        pdfDocument.writeTo(new FileOutputStream(file));
//        Toast.makeText(this, "PDF saved to Downloads folder!", Toast.LENGTH_LONG).show();
//    } catch (IOException e) {
//        e.printStackTrace();
//        Toast.makeText(this, "Error saving PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
//    }
//
//    // Close the document
//    pdfDocument.close();
//}
//private void createPdf(String invitationMessage) {
//    // Create a new PdfDocument
//    PdfDocument pdfDocument = new PdfDocument();
//
//    // Create a page description with a wider page size for better flexibility
//    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(500, 700, 1).create();
//
//    // Start a new page
//    PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//
//    // Get the canvas of the page
//    Canvas canvas = page.getCanvas();
//
//    // Set up paint for the background
//    Paint backgroundPaint = new Paint();
//    backgroundPaint.setColor(Color.parseColor("#FFEBEE")); // Light pinkish background
//    canvas.drawRect(0, 0, pageInfo.getPageWidth(), pageInfo.getPageHeight(), backgroundPaint);
//
//    // Set up paint for title text
//    Paint titlePaint = new Paint();
//    titlePaint.setColor(Color.parseColor("#D32F2F")); // Dark red for title
//    titlePaint.setTextSize(32);
//    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
//    titlePaint.setTextAlign(Paint.Align.CENTER);
//
//    // Draw the title
//    canvas.drawText("Wedding Invitation", pageInfo.getPageWidth() / 2, 60, titlePaint);
//
//    // Set up paint for the invitation message
//    Paint messagePaint = new Paint();
//    messagePaint.setColor(Color.BLACK);
//    messagePaint.setTextSize(18);
//    messagePaint.setTextAlign(Paint.Align.LEFT);
//
//    // Draw the bride and groom names in a special font style
//    Paint coupleNamePaint = new Paint();
//    coupleNamePaint.setColor(Color.parseColor("#1976D2")); // Blue color for names
//    coupleNamePaint.setTextSize(24);
//    coupleNamePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
//
//    // Draw the couple names
//    canvas.drawText("Bride: " + brideName, 30, 100, coupleNamePaint);
//    canvas.drawText("Groom: " + groomName, 30, 140, coupleNamePaint);
//
//    // Set up the text for the message with word wrap and margins
//    float marginLeft = 30;
//    float marginRight = pageInfo.getPageWidth() - 30;
//    float yPosition = 180; // Start below the couple's names
//
//    // Split the invitation message into lines and wrap text
//    String[] lines = invitationMessage.split("\n");
//    for (String line : lines) {
//        // Handle word wrapping if the text is too wide
//        String[] words = line.split(" ");
//        StringBuilder currentLine = new StringBuilder();
//        for (String word : words) {
//            float textWidth = messagePaint.measureText(currentLine + word + " ");
//            if (textWidth < (marginRight - marginLeft)) {
//                currentLine.append(word).append(" ");
//            } else {
//                canvas.drawText(currentLine.toString(), marginLeft, yPosition, messagePaint);
//                yPosition += messagePaint.getTextSize() + 10; // Add spacing
//                currentLine = new StringBuilder(word).append(" ");
//            }
//        }
//        canvas.drawText(currentLine.toString(), marginLeft, yPosition, messagePaint);
//        yPosition += messagePaint.getTextSize() + 10; // Add spacing between lines
//    }
//
//    // Add the bride and groom images side by side, centered below the text
//    if (isBrideImageSelected && isGroomImageSelected) {
//        Bitmap brideBitmap = ((BitmapDrawable) brideImageView.getDrawable()).getBitmap();
//        Bitmap groomBitmap = ((BitmapDrawable) groomImageView.getDrawable()).getBitmap();
//        Bitmap scaledBrideBitmap = Bitmap.createScaledBitmap(brideBitmap, 100, 100, false);
//        Bitmap scaledGroomBitmap = Bitmap.createScaledBitmap(groomBitmap, 100, 100, false);
//
//        // Calculate positions for images
//        int imageStartX = (pageInfo.getPageWidth() / 2) - 120; // Adjust to center images
//        int imageY = (int) (yPosition + 20); // Adjust Y position below text
//
//        // Draw the images
//        canvas.drawBitmap(scaledBrideBitmap, imageStartX, imageY, null);
//        canvas.drawBitmap(scaledGroomBitmap, imageStartX + 140, imageY, null); // Space between images
//        yPosition = imageY + 120; // Update yPosition after images
//    }
//
//    // Draw a decorative border
//    Paint borderPaint = new Paint();
//    borderPaint.setColor(Color.parseColor("#B71C1C")); // Dark red for border
//    borderPaint.setStyle(Paint.Style.STROKE);
//    borderPaint.setStrokeWidth(4);
//    canvas.drawRect(20, 20, pageInfo.getPageWidth() - 20, pageInfo.getPageHeight() - 20, borderPaint);
//
//    // Draw footer text with a different font and style
//    Paint footerPaint = new Paint();
//    footerPaint.setColor(Color.parseColor("#303F9F")); // Dark blue for footer text
//    footerPaint.setTextSize(16);
//    footerPaint.setTextAlign(Paint.Align.CENTER);
//    canvas.drawText("Join us for a day full of love, laughter, and memories!", pageInfo.getPageWidth() / 2, pageInfo.getPageHeight() - 40, footerPaint);
//
//    // Finish the page
//    pdfDocument.finishPage(page);
//
//    // Save the document to device storage
//    String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//    File file = new File(directoryPath, "WeddingInvitation.pdf");
//
//    try {
//        pdfDocument.writeTo(new FileOutputStream(file));
//        Toast.makeText(this, "PDF saved to Downloads folder!", Toast.LENGTH_LONG).show();
//    } catch (IOException e) {
//        e.printStackTrace();
//        Toast.makeText(this, "Error saving PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
//    }
//
//    // Close the document
//    pdfDocument.close();
//}
private void createPdf(String invitationMessage) {
    // Create a new PdfDocument
    PdfDocument pdfDocument = new PdfDocument();

    // Create a page description with a wider page size for better flexibility
    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(500, 700, 1).create();

    // Start a new page
    PdfDocument.Page page = pdfDocument.startPage(pageInfo);

    // Get the canvas of the page
    Canvas canvas = page.getCanvas();

    // Set up paint for the background
    Paint backgroundPaint = new Paint();
    backgroundPaint.setColor(Color.parseColor("#FFEBEE")); // Light pinkish background
    canvas.drawRect(0, 0, pageInfo.getPageWidth(), pageInfo.getPageHeight(), backgroundPaint);

    // Set up paint for title text
    Paint titlePaint = new Paint();
    titlePaint.setColor(Color.parseColor("#D32F2F")); // Dark red for title
    titlePaint.setTextSize(32);
    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
    titlePaint.setTextAlign(Paint.Align.CENTER);

    // Draw the title
    canvas.drawText("Wedding Invitation", pageInfo.getPageWidth() / 2, 60, titlePaint);

    // Set up paint for the invitation message
    Paint messagePaint = new Paint();
    messagePaint.setColor(Color.BLACK);
    messagePaint.setTextSize(18);
    messagePaint.setTextAlign(Paint.Align.LEFT);

    // Draw the bride and groom names in a special font style
    Paint coupleNamePaint = new Paint();
    coupleNamePaint.setColor(Color.parseColor("#1976D2")); // Blue color for names
    coupleNamePaint.setTextSize(24);
    coupleNamePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));

    // Draw the couple names
    canvas.drawText("Bride: " + brideName, 30, 100, coupleNamePaint);
    canvas.drawText("Groom: " + groomName, 30, 140, coupleNamePaint);

    // Set up the text for the message with word wrap and margins
    float marginLeft = 30;
    float marginRight = pageInfo.getPageWidth() - 30;
    float yPosition = 180; // Start below the couple's names

    // Split the invitation message into lines and wrap text
    String[] lines = invitationMessage.split("\n");
    for (String line : lines) {
        // Handle word wrapping if the text is too wide
        String[] words = line.split(" ");
        StringBuilder currentLine = new StringBuilder();
        for (String word : words) {
            float textWidth = messagePaint.measureText(currentLine + word + " ");
            if (textWidth < (marginRight - marginLeft)) {
                currentLine.append(word).append(" ");
            } else {
                canvas.drawText(currentLine.toString(), marginLeft, yPosition, messagePaint);
                yPosition += messagePaint.getTextSize() + 10; // Add spacing
                currentLine = new StringBuilder(word).append(" ");
            }
        }
        canvas.drawText(currentLine.toString(), marginLeft, yPosition, messagePaint);
        yPosition += messagePaint.getTextSize() + 10; // Add spacing between lines
    }

    // Add the bride and groom images side by side, centered below the text
//    if (isBrideImageSelected && isGroomImageSelected) {
//        Bitmap brideBitmap = ((BitmapDrawable) brideImageView.getDrawable()).getBitmap();
//        Bitmap groomBitmap = ((BitmapDrawable) groomImageView.getDrawable()).getBitmap();
//        Bitmap scaledBrideBitmap = Bitmap.createScaledBitmap(brideBitmap, 100, 100, false);
//        Bitmap scaledGroomBitmap = Bitmap.createScaledBitmap(groomBitmap, 100, 100, false);
//
//        // Calculate positions for images
//        int imageStartX = (pageInfo.getPageWidth() / 2) - 120; // Adjust to center images
//        int imageY = (int) (yPosition + 20); // Adjust Y position below text
//
//        // Draw the images
//        canvas.drawBitmap(scaledBrideBitmap, imageStartX, imageY, null);
//        canvas.drawBitmap(scaledGroomBitmap, imageStartX + 140, imageY, null); // Space between images
//        yPosition = imageY + 120; // Update yPosition after images
//    }

    // Draw a decorative border
    Paint borderPaint = new Paint();
    borderPaint.setColor(Color.parseColor("#B71C1C")); // Dark red for border
    borderPaint.setStyle(Paint.Style.STROKE);
    borderPaint.setStrokeWidth(4);
    canvas.drawRect(20, 20, pageInfo.getPageWidth() - 20, pageInfo.getPageHeight() - 20, borderPaint);

    // Draw footer text with a different font and style
    Paint footerPaint = new Paint();
    footerPaint.setColor(Color.parseColor("#303F9F")); // Dark blue for footer text
    footerPaint.setTextSize(16);
    footerPaint.setTextAlign(Paint.Align.CENTER);
    canvas.drawText("Join us for a day full of love, laughter, and memories!", pageInfo.getPageWidth() / 2, pageInfo.getPageHeight() - 40, footerPaint);

    // Finish the page
    pdfDocument.finishPage(page);

    // Save the document to device storage
    String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
    File file = new File(directoryPath, "WeddingInvitation.pdf");

    try {
        pdfDocument.writeTo(new FileOutputStream(file));
        Toast.makeText(this, "PDF saved to Downloads folder!", Toast.LENGTH_LONG).show();
    } catch (IOException e) {
        e.printStackTrace();
        Toast.makeText(this, "Error saving PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    // Close the document
    pdfDocument.close();
}

    // Method to display the invitation message and images
    private void displayInvitation(String invitationMessage) {
        // Hide all other UI elements
        guestNameEditText.setVisibility(View.GONE);
        brideImageView.setVisibility(View.VISIBLE);
        groomImageView.setVisibility(View.VISIBLE);
        findViewById(R.id.selectBrideImageButton).setVisibility(View.GONE);
        findViewById(R.id.selectGroomImageButton).setVisibility(View.GONE);
        findViewById(R.id.sendInvitationButton).setVisibility(View.GONE);

        // Set the invitation message
        invitationTextView.setText(invitationMessage);
        invitationTextView.setVisibility(View.VISIBLE);

        // Show the download PDF button
        Button downloadPdfButton = new Button(this);
        downloadPdfButton.setText("Download Invitation PDF");
        downloadPdfButton.setVisibility(View.VISIBLE);

        // Add the button to the layout
        LinearLayout layout = findViewById(R.id.invitationLayout); // Ensure 'invitationLayout' exists in your XML
        layout.addView(downloadPdfButton);

        // Set the download PDF button's click listener
        downloadPdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPdf(invitationMessage);
            }
        });
    }
}
