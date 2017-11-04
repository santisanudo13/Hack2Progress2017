# -*- coding: utf-8 -*-

#
# Try:
# 0 Hidden layers
# 1 Hidden layer with 10 and 100 neurons
# 3 Hidden layers with 100 neurons
# Change the optimizer and try Nadam


import keras
from keras.datasets import mnist
from keras.models import Sequential
from keras.layers import Dense, Activation, Dropout
from keras.optimizers import RMSprop,Nadam
import numpy as np

#libsvm format
import sklearn
from sklearn.datasets import load_svmlight_file
from sklearn.preprocessing import StandardScaler

print("Loading training data")
x, y = load_svmlight_file('covtype.libsvm.binary.scale',54)
x=x.toarray()

perm =np.random.permutation(len(x))
x=x[perm,:]
y=y[perm]

y[y<1.5]=0.0
y[y>1.5]=1.0

x_train=x[0:300000,:]
x_test=x[300001:,:]
y_train=y[0:300000]
y_test=y[300001:]

print x_train.shape
print y_train.shape

print x_test.shape
print y_test.shape


batch_size = 1024
epochs = 500



model = Sequential()
model.add(Dense(100, activation='relu', input_shape=(54,)))
model.add(Dense(100, activation='relu'))
model.add(Dense(100, activation='relu'))
model.add(Dense(1, activation='sigmoid'))

model.summary()

model.compile(loss='binary_crossentropy', optimizer=Nadam(), metrics=['accuracy'])

history = model.fit(x_train, y_train, batch_size=batch_size, epochs=epochs, verbose=1, validation_data=(x_test, y_test))

score = model.evaluate(x_test, y_test, verbose=0)

print('Test loss: ', score[0])
print('Test accuracy: ', score[1]) 


# ...


#model.compile ...


#history = model.fit(



