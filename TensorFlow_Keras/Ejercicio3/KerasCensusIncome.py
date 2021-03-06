# -*- coding: utf-8 -*-

#
# Construir una red neuronal sin capas ocultas
# Utilizar una capa oculta con 100 neuronas
# ¿Qué ocurre si quitamos la función de activación a la salida?
#
#

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
x_train, y_train = load_svmlight_file('a9a',123)
x_train=x_train.toarray()
y_train[y_train<0]=0.0
print x_train.shape
print y_train.shape

print("Loading test data")
x_test, y_test = load_svmlight_file('a9a.t',123)
x_test=x_test.toarray()
y_test[y_test<0]=0.0
print x_train.shape
print y_train.shape

print("Normalizing")
scaler=StandardScaler().fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)



batch_size = 128
num_classes = 2
epochs = 20


model = Sequential()
model.add(Dense(100, activation='relu', input_shape=(123,)))
model.add(Dense(100, activation='relu'))
model.add(Dense(100, activation='relu'))
model.add(Dense(1, activation='sigmoid'))

#model = Sequential()
#model.add ...
# ...


#model.compile ...


#history = model.fit(


