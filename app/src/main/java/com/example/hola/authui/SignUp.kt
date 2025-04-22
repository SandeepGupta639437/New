package com.example.hola.authui

@Composable
fun SignUpScreen() {
    val fullName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF2C003E), Color(0xFF5C006D))))
            .padding(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Illustrative image placeholder
            Image(
                painter = painterResource(id = R.drawable.signup_illustration),
                contentDescription = "Sign Up Illustration",
                modifier = Modifier.height(120.dp)
            )

            Text("Sign Up", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text("Use proper information to continue", color = Color.LightGray, fontSize = 14.sp)

            OutlinedTextField(
                value = fullName.value,
                onValueChange = { fullName.value = it },
                label = { Text("Full Name") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    leadingIconColor = Color.White,
                    focusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email Address") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    leadingIconColor = Color.White,
                    focusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White,
                    leadingIconColor = Color.White,
                    focusedLabelColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { /* Handle Sign Up */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9C89F6)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Account", color = Color.White)
            }

            Text(
                text = "By signing up, you agree to our Terms & Conditions and Privacy and Policy",
                color = Color.LightGray,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(modifier = Modifier.weight(1f), color = Color.Gray)
                Text("  Or  ", color = Color.LightGray)
                Divider(modifier = Modifier.weight(1f), color = Color.Gray)
            }

            OutlinedButton(
                onClick = { /* Google Sign In */ },
                border = BorderStroke(1.dp, Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Icon",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Continue with Google", color = Color.White)
            }

            Row {
                Text("Already have an account? ", color = Color.LightGray)
                Text(
                    "Sign in",
                    color = Color(0xFF9C89F6),
                    modifier = Modifier.clickable { /* Navigate to Sign In */ }
                )
            }
        }
    }
}
