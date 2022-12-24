# "Convolutional  Neural  Network"  indicates  that  the network  employs  amathematical operation called convolution

# CIFAR-10
# The CIFAR-10 dataset (Canadian Institute For Advanced Research) is a collection of images 
# that are commonly used to train machine learning and computer vision algorithms

# Import modules
import tensorflow as tf
from tensorflow.keras import datasets, layers, models
from keras.layers import Dropout
from keras.constraints import maxnorm
import matplotlib.pyplot as plt

# Prepare data for training & testing
(train_images, train_labels), (test_images, test_labels) = datasets.cifar10.load_data()
train_images = train_images/255.0
test_images = test_images/255.0

model = models.Sequential()

# Apply 32 masks with 3 X 3 size
model.add(layers.Conv2D(32, (3, 3), input_shape=(32, 32, 3), activation='relu', padding='same'))
model.add(Dropout(0.2)) # drop out 0.2: Randomly turn off 20% of nodes located in the hidden layers
model.add(layers.Conv2D(32, (3, 3), activation='relu', padding='same'))
model.add(layers.MaxPooling2D((2, 2))) # Max Pooling (Take the maximum value)


# Apply 64 masks with 3 X 3 size
model.add(layers.Conv2D(64, (3, 3), activation='relu', padding='same'))
model.add(Dropout(0.2))
model.add(layers.Conv2D(64, (3, 3), activation='relu', padding='same'))
model.add(layers.MaxPooling2D((2, 2)))

# Apply 128 masks with 3 X 3 size
model.add(layers.Conv2D(128, (3, 3), activation='relu', padding='same'))
model.add(Dropout(0.2))
model.add(layers.Conv2D(128, (3, 3), activation='relu', padding='same'))
model.add(layers.MaxPooling2D((2, 2)))

# Flatten : Convert 2-dimensional array into 1-dimensional array

model.add(layers.Flatten())
model.add(Dropout(0.3))

# The layer with 512 nodes
model.add(layers.Dense(512, activation='relu', kernel_constraint=maxnorm(3)))
model.add(Dropout(0.3))

# The layer with 256 nodes
model.add(layers.Dense(256, activation='relu', kernel_constraint=maxnorm(3)))
model.add(Dropout(0.3))

# The layer with 128 nodes
model.add(layers.Dense(128, activation='relu', kernel_constraint=maxnorm(3)))
model.add(Dropout(0.3))

# Output layer Creation
model.add(layers.Dense(10, activation='softmax'))
model.summary()

# Use one-hot-encoding to use Categorical Cross Entropy for loss function
train_labels = tf.keras.utils.to_categorical(train_labels, 10)
test_labels = tf.keras.utils.to_categorical(test_labels, 10)

# Specify optimizer (ADAM) & loss function(CategoricalCrossentropy)
model.compile(optimizer='adam', loss=tf.keras.losses.CategoricalCrossentropy(from_logits=True),metrics=['accuracy'])

# Training
history=model.fit(train_images, train_labels, epochs=100, batch_size= 100, validation_data=(test_images, test_labels))

test_loss, test_acc = model.evaluate(test_images,  test_labels, verbose=2)
print(f"test_accuaracy is {test_acc:.3}")
print(f"test_loss is {test_loss:.3}")

# Draw plot of accuracy

plt.plot(history.history['accuracy'], label='accuracy')
plt.plot(history.history['val_accuracy'], label = 'val_accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.ylim([0.5, 1])
plt.legend(loc='lower right')
plt.show()

#Draw plot of loss

plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.title('model loss')
plt.ylabel('loss')
plt.xlabel('epoch')
plt.legend(['training', 'validation'], loc='best')
plt.show()